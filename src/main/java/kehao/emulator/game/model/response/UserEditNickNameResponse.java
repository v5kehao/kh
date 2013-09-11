package kehao.emulator.game.model.response;

public class UserEditNickNameResponse extends GameData {
  public static final String DuplicateNickNameMessage = "昵称已存在!";
  public static final String TooLongMessage = "昵称只能7位以下哦!";

  public boolean duplicateNickName() {
    return message != null && message.equals(DuplicateNickNameMessage);
  }

  public boolean tooLong() {
    return message != null && message.equals(TooLongMessage);
  }
}
