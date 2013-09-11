package kehao.emulator.game.model.basic;

public class BattleMapExtData extends BattleExtData {
  private Integer StarUp;
  private String[] Bonus;
  private String[] FirstBonusWin;
  private int UserLevel;
  private long Exp;
  private long PrevExp;
  private long NextExp;

  public Integer getStarUp() {
    return StarUp;
  }

  public void setStarUp(Integer starUp) {
    StarUp = starUp;
  }

  public String[] getBonus() {
    return Bonus;
  }

  public void setBonus(String[] bonus) {
    Bonus = bonus;
  }

  public int getUserLevel() {
    return UserLevel;
  }

  public void setUserLevel(int userLevel) {
    UserLevel = userLevel;
  }

  public long getExp() {
    return Exp;
  }

  public void setExp(long exp) {
    Exp = exp;
  }

  public long getPrevExp() {
    return PrevExp;
  }

  public void setPrevExp(long prevExp) {
    PrevExp = prevExp;
  }

  public long getNextExp() {
    return NextExp;
  }

  public void setNextExp(long nextExp) {
    NextExp = nextExp;
  }

  public String[] getFirstBonusWin() {
    return FirstBonusWin;
  }

  public void setFirstBonusWin(String[] firstBonusWin) {
    FirstBonusWin = firstBonusWin;
  }
}
