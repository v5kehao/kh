package kehao.emulator.web.model.request;

import kehao.emulator.web.model.response.LoginInformationResponse;

public class LoginRequest extends PassportRequest<LoginInformationResponse> {

  public LoginRequest(String username, String password, String mac) {
    argMap.put("userName", username);
    argMap.put("userPassword", password);
    argMap.put("gameName", "CARD-ANDROID-CHS");
    argMap.put("udid", mac);
    argMap.put("clientType", "flash");
    argMap.put("releaseChannel", "");
    argMap.put("locale", "");
  }

  @Override
  public String getFunc() {
    return "login";
  }
}
