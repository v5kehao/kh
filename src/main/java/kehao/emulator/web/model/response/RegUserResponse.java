package kehao.emulator.web.model.response;

public class RegUserResponse extends StringResponse {

  public static final String DuplicateUserNameMessage = "Game is already activated by this user.";

  public boolean duplicateUsername() {
    String message = getReturnMsg();
    return message != null && message.equals(DuplicateUserNameMessage);
  }
}
