package kehao.emulator;

import java.util.*;

import kehao.emulator.game.MkbGame;
import kehao.emulator.game.model.response.GameData;
import kehao.emulator.game.model.response.GameDataFactory;
import kehao.emulator.model.GameVersion;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorCore {

  @Autowired
  private UnknownErrorHandler unknownErrorHandler;
  @Autowired
  private GameVersion gameVersion;
  @Autowired
  private EmulatorWeb web;
  @Autowired
  private EmulatorLogin login;
  @Autowired
  private AccountCredentialProvider accountCredentialProvider;

  private ClientConnectionManager connectionManager = new PoolingClientConnectionManager();
  private Map<String, DefaultHttpClient> httpClients = new HashMap<>();
  private List<DefaultHttpClient> freeClients = new ArrayList<>();
  private Map<String, MkbGame> games = new HashMap<>();
  private Map<String, LoginToken> loginTokens = new HashMap<>();

  private DefaultHttpClient getHttpClient(String username) {
    DefaultHttpClient httpClient = httpClients.get(username);
    if(httpClient == null) {
      if(!freeClients.isEmpty()) {
        httpClient = freeClients.remove(0);
      } else {
        httpClient = new DefaultHttpClient(connectionManager);
      }
      httpClients.put(username, httpClient);
    }
    return httpClient;
  }

  public MkbGame getMkbGame(String username) throws ServerNotAvailableException, WrongCredentialException {
    MkbGame game = games.get(username);
    if(game != null) return game;
    LoginToken token = loginTokens.get(username);
    if(token == null) {
      BasicAccount account = accountCredentialProvider.getAccount(username);
      token = web.login(account.getUsername(), account.getPassword(), account.getMac());
      loginTokens.put(username, token);
    }
    game = new MkbGame(token.getHost(), getHttpClient(username), gameVersion.getPlatform(), gameVersion.getLanguage(), gameVersion.getVersionClient(), gameVersion.getVersionBuild());
    games.put(username, game);
    login.passportLogin(username);
    return game;
  }

  public String gameDoAction(String username, String service, String action, Map<String, String> paramMap) throws ServerNotAvailableException, WrongCredentialException {
    MkbGame core = getMkbGame(username);
    String ret = core.doAction(service, action, paramMap);
    if(ret == null) {
      throw new ServerNotAvailableException();
    }
    return ret;
  }

  public <T extends GameData> T gameDoAction(String username, String service, String action, Map<String, String> paramMap, Class<T> clazz) throws ServerNotAvailableException, WrongCredentialException {
    String responseString = gameDoAction(username, service, action, paramMap);
    T response = GameDataFactory.getGameData(responseString, clazz);
    if(response.disconnected()) {
      reconnect(username);
      return gameDoAction(username, service, action, paramMap, clazz);
    }
    return response;
  }

  private void reconnect(String username) throws ServerNotAvailableException, WrongCredentialException {
    LoginToken token = loginTokens.get(username);
    games.remove(username);
    loginTokens.remove(username);
    token = web.login(token.getUsername(), token.getPassword(), token.getMac());
    setLoginToken(username, token);
    getMkbGame(username);
    login.passportLogin(username);
  }

  public void setLoginToken(String username, LoginToken loginToken) {
    loginTokens.put(username, loginToken);
  }

  public LoginToken getLoginToken(String username) {
    return loginTokens.get(username);
  }

  public void insureConnection(String username, String password, String mac) throws WrongCredentialException, ServerNotAvailableException {
    MkbGame game = games.get(username);
    if(game == null) {
      LoginToken token = web.login(username, password, mac);
      loginTokens.put(username, token);
      getMkbGame(username);
    }
  }
}
