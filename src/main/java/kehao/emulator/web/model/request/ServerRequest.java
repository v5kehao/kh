package kehao.emulator.web.model.request;

import kehao.emulator.web.model.response.ServerInformationResponse;

public class ServerRequest extends PassportRequest<ServerInformationResponse> {

  public ServerRequest() {
    argMap.put("gameName", "CARD-ANDROID-CHS");
    argMap.put("locale", "");
    argMap.put("udid", "null");
  }

  @Override
  public String getFunc() {
    return "getLoginGameServers";
  }
}
