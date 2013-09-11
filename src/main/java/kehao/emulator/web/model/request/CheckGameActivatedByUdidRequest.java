package kehao.emulator.web.model.request;

import kehao.emulator.web.model.response.StringResponse;

public class CheckGameActivatedByUdidRequest extends PassportRequest<StringResponse> {

  public CheckGameActivatedByUdidRequest(String username, long serverId, String mac) {
    argMap.put("udid", mac);
    argMap.put("gameName", "CARD-ANDROID-CHS");
    argMap.put("userName", username);
    argMap.put("friendCode", "");
    argMap.put("selGsId", Long.toString(serverId));
  }

  @Override
  public String getFunc() {
    return "checkGameActivatedByUdid";
  }
}
