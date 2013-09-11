package kehao.emulator.game.model.basic;

public class LegionInfo {
  private int LegionId;
  private String name;
  private String Emblem;
  private String EmblemLevel;
  private long Coins;
  private String[] FightUserList;

  public int getLegionId() {
    return LegionId;
  }

  public String getName() {
    return name;
  }

  public String getEmblem() {
    return Emblem;
  }

  public String getEmblemLevel() {
    return EmblemLevel;
  }

  public long getCoins() {
    return Coins;
  }

  public String[] getFightUserList() {
    return FightUserList;
  }
}
