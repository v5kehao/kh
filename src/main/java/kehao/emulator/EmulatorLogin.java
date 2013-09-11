package kehao.emulator;

import java.util.LinkedHashMap;
import java.util.Map;

import kehao.emulator.game.model.basic.PassportLogin;
import kehao.emulator.game.model.response.LoginPassportLoginResponse;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorLogin {

  @Autowired
  private UnknownErrorHandler unknownErrorHandler;
  @Autowired
  private EmulatorCore core;

  public PassportLogin passportLogin(String username) throws ServerNotAvailableException,
                                                               WrongCredentialException {
    LoginToken token = core.getLoginToken(username);
    if(token == null) {
      return null;
    }
    Map<String, String> paramMap = new LinkedHashMap<>();
    paramMap.put("time", Long.toString(token.getTime()));
    paramMap.put("key", token.getKey());
    paramMap.put("Udid", token.getMac());
    paramMap.put("Password", Long.toString(token.getUid()));
    paramMap.put("Devicetoken", "");
    paramMap.put("UserName", username.toLowerCase());
    paramMap.put("Origin", "com");
    LoginPassportLoginResponse response = core.gameDoAction(username, "login.php", "PassportLogin", paramMap, LoginPassportLoginResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

}
