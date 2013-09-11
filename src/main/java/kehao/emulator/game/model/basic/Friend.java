package kehao.emulator.game.model.basic;

public class Friend {
  private long Uid;
  private String NickName;
  private int Level;
  private int Avatar;
  private int Sex;
  private int Win;
  private int Lose;
  private int Rank;
  private String LastLogin;
  private String LegionName;
  private long Coins;
  private int FriendNum;
  private int FriendNumMax;
  private int FEnergySurplus;
  private int FEnergySend;

  public boolean isGiftable() {
    return FEnergySend != 0;
  }

  public boolean isCollectable() {
    return FEnergySurplus != 0;
  }

  public long getUid() {
    return Uid;
  }

  public String getNickName() {
    return NickName;
  }

  public int getLevel() {
    return Level;
  }

  public int getAvatar() {
    return Avatar;
  }

  public int getSex() {
    return Sex;
  }

  public int getWin() {
    return Win;
  }

  public int getLose() {
    return Lose;
  }

  public int getRank() {
    return Rank;
  }

  public String getLastLogin() {
    return LastLogin;
  }

  public String getLegionName() {
    return LegionName;
  }

  public long getCoins() {
    return Coins;
  }

  public int getFriendNum() {
    return FriendNum;
  }

  public int getFriendNumMax() {
    return FriendNumMax;
  }

  public int getFEnergySurplus() {
    return FEnergySurplus;
  }

  public int getFEnergySend() {
    return FEnergySend;
  }
}
