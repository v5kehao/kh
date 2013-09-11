package kehao.emulator.game.model.response;

import kehao.emulator.game.model.basic.UserInfo;

public class UserGetUserInfoResponse extends GameData<UserInfo> {
  public static final String RequireNickName = "请输入昵称"; // 请输入昵称!

  public boolean isRequireNickName() {
    return message != null && message.contains(RequireNickName);
  }
}
