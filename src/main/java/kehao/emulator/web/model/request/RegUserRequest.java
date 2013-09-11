package kehao.emulator.web.model.request;

import kehao.emulator.web.model.response.RegUserResponse;

public class RegUserRequest extends PassportRequest<RegUserResponse> {

  public RegUserRequest(String username, String password, String mac, long serverId) {
    argMap.put("userName", username);
    argMap.put("email", "");
    argMap.put("userPassword", password);
    argMap.put("gameName", "CARD-ANDROID-CHS");
    argMap.put("udid", mac);
    argMap.put("clientType", "flash");
    argMap.put("releaseChannel", "");
    argMap.put("locale", "");
    argMap.put("friendCode", "");
    argMap.put("selGsId", Long.toString(serverId));
  }

  @Override
  public String getFunc() {
    return "regUser";
  }
}
