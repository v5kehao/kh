package kehao.emulator.game.model.basic;

import java.util.List;

public class Player {
  private long Uid;
  private String NickName;
  private int Avatar;
  private int Sex;
  private int Level;
  private int HP;
  private List<BattleCard> Cards;
  private List<BattleRune> Runes;
  private int RemainHP;

  public long getUid() {
    return Uid;
  }

  public String getNickName() {
    return NickName;
  }

  public int getAvatar() {
    return Avatar;
  }

  public int getSex() {
    return Sex;
  }

  public int getLevel() {
    return Level;
  }

  public int getHP() {
    return HP;
  }

  public List<BattleCard> getCards() {
    return Cards;
  }

  public List<BattleRune> getRunes() {
    return Runes;
  }

  public int getRemainHP() {
    return RemainHP;
  }

  @Override
  public String toString() {
    return "Player " + Uid + " " + NickName;
  }

  public class BattleCard {
    private String UUID;
    private int CardId;
    private long UserCardId;
    private int Attack;
    private int HP;
    private int Wait;
    private int Level;
    private String SkillNew;
    private String Evolution;
    private String WashTime;

    public String getUUID() {
      return UUID;
    }

    public int getCardId() {
      return CardId;
    }

    public long getUserCardId() {
      return UserCardId;
    }

    public int getAttack() {
      return Attack;
    }

    public int getHP() {
      return HP;
    }

    public int getWait() {
      return Wait;
    }

    public int getLevel() {
      return Level;
    }

    public String getSkillNew() {
      return SkillNew;
    }

    public String getEvolution() {
      return Evolution;
    }

    public String getWashTime() {
      return WashTime;
    }
  }

  public class BattleRune {
    private String UUID;
    private int RuneId;
    private long UserRuneId;
    private int Level;

    public String getUUID() {
      return UUID;
    }

    public int getRuneId() {
      return RuneId;
    }

    public long getUserRuneId() {
      return UserRuneId;
    }

    public int getLevel() {
      return Level;
    }
  }
}
