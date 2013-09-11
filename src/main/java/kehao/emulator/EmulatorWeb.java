package kehao.emulator;

import java.util.List;

import kehao.emulator.web.MkbWeb;
import kehao.emulator.web.model.basic.GameServer;
import kehao.emulator.web.model.basic.LoginInformation;
import kehao.emulator.web.model.basic.ServerInformation;
import kehao.emulator.web.model.request.*;
import kehao.emulator.web.model.response.*;
import kehao.exception.web.UsernameExistsException;
import kehao.exception.WrongCredentialException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorWeb {

  @Autowired
  private UnknownErrorHandler unknownErrorHandler;

  private DefaultHttpClient shared = new DefaultHttpClient();

  private <T extends ReturnTemplate> T passportRequest(PassportRequest<T> passportRequest, Class<T> clazz) {
    MkbWeb helper = new MkbWeb(shared);
    helper.requestEncryptKey();
    helper.proposeCounterKey();
    return helper.sendRequest(passportRequest, clazz);
  }

  public LoginToken login(String username, String password, String mac) throws WrongCredentialException {
    LoginInformationResponse loginInformation = passportRequest(new LoginRequest(username, password, mac), LoginInformationResponse.class);
    LoginInformation ret = loginInformation.getReturnObjs();
    if(!loginInformation.badRequest()) {
      return new LoginToken(ret.getGS_DESC(), ret.getGS_IP(), username, password, ret.getU_ID(), mac, ret.getKey(), ret.getTimestamp());
    }
    throw new WrongCredentialException();
  }

  public boolean register(String username, String password, String mac, long serverId) throws UsernameExistsException {
    RegUserResponse response = passportRequest(new RegUserRequest(username, password, mac, serverId), RegUserResponse.class);
    if(response.badRequest()) {
      if(response.duplicateUsername()) {
        throw new UsernameExistsException();
      }
      unknownErrorHandler.print(username, response.getReturnMsg());
    }
    return true;
  }

  public List<GameServer> getServers() {
    ServerInformation serverInformation = passportRequest(new ServerRequest(), ServerInformationResponse.class).getReturnObjs();
    return serverInformation.getGAME_SERVER();
  }

}
