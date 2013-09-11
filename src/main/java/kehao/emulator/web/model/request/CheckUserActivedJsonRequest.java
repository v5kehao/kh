package kehao.emulator.web.model.request;

import kehao.emulator.web.model.response.StringResponse;

public class CheckUserActivedJsonRequest extends PassportRequest<StringResponse> {

  public CheckUserActivedJsonRequest(String username, String password, long serverId, String mac) {
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
    return "checkUserActivedJson";
  }
}
