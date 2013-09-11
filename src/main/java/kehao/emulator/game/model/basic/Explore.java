package kehao.emulator.game.model.basic;

public class Explore extends EnergyUser {
  private String[] Bonus; // Exp_*, Coins_*, Card_*, Chip_*
  private int UserLevel;
  private long Exp;
  private long PrevExp;
  private long NextExp;
  private ThievesInfo ThievesInfo;

  public int getChip() {
    for(String b : Bonus) {
      if(b.startsWith("Chip_")) return Integer.parseInt(b.substring(5));
    }
    return -1;
  }

  public String[] getBonus() {
    return Bonus;
  }

  public int getUserLevel() {
    return UserLevel;
  }

  public long getExp() {
    return Exp;
  }

  public long getPrevExp() {
    return PrevExp;
  }

  public long getNextExp() {
    return NextExp;
  }

  public ThievesInfo getThievesInfo() {
    return ThievesInfo;
  }
}
