package kehao.emulator.game.model.basic;

public class BattleNormalExtData extends BattleExtData {

  private Award Award;
  private Clear Clear;
  private User User;

  public BattleNormalExtData.Award getAward() {
    return Award;
  }

  public void setAward(BattleNormalExtData.Award award) {
    Award = award;
  }

  public BattleNormalExtData.Clear getClear() {
    return Clear;
  }

  public void setClear(BattleNormalExtData.Clear clear) {
    Clear = clear;
  }

  public BattleNormalExtData.User getUser() {
    return User;
  }

  public void setUser(BattleNormalExtData.User user) {
    User = user;
  }

  public class Award {
    private int Coins;
    private int Exp;
    private int CardId;
    private int Honor;
    private int Chip;

    public int getCoins() {
      return Coins;
    }

    public int getExp() {
      return Exp;
    }

    public int getCardId() {
      return CardId;
    }

    public int getChip() {
      return Chip;
    }

    public int getHonor() {
      return Honor;
    }
  }

  public class Clear {
    private int IsClear;
    private int CardId;
    private int Coins;

    public boolean clear() {
      return getIsClear() > 0;
    }

    public int getIsClear() {
      return IsClear;
    }

    public int getCardId() {
      return CardId;
    }

    public int getCoins() {
      return Coins;
    }

  }

  public class User {
    private int Level;
    private long Exp;
    private long PrevExp;
    private long NextExp;

    public int getLevel() {
      return Level;
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
  }
}
