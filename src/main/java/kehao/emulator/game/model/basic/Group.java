package kehao.emulator.game.model.basic;

import java.util.List;

public class Group {
  private long Uid;
  private String UserCardIds;
  private String UserRuneIds;
  private long GroupId;
  private List<UserCard> UserCardInfo;
  private List<UserRune> UserRune;

  public long getUid() {
    return Uid;
  }

  public String getUserCardIds() {
    return UserCardIds;
  }

  public String getUserRuneIds() {
    return UserRuneIds;
  }

  public long getGroupId() {
    return GroupId;
  }

  public List<UserCard> getUserCardInfo() {
    return UserCardInfo;
  }

  public List<UserRune> getUserRune() {
    return UserRune;
  }
}
