package kehao.emulator.game.model.basic;

public class UserCard {
  protected long Uid;
  protected int CardId;
  protected int Level;
  protected long Exp;
  protected long UserCardId;
  protected int SkillNew;
  protected int Evolution;
  protected int WashTime;

  public long getUid() {
    return Uid;
  }

  public int getCardId() {
    return CardId;
  }

  public int getLevel() {
    return Level;
  }

  public long getExp() {
    return Exp;
  }

  public long getUserCardId() {
    return UserCardId;
  }

  public int getSkillNew() {
    return SkillNew;
  }

  public int getEvolution() {
    return Evolution;
  }

  public int getWashTime() {
    return WashTime;
  }

  public boolean evolved() {
    return getEvolution() != 0;
  }
}
