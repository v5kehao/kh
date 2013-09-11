package kehao.emulator.game.model.basic;

public class CardDef {
  private int CardId;
  private String CardName;
  private int Cost;
  private int Color;
  private int Race;
  private int Attack;
  private int Wait;
  private String Skill;
  private String LockSkill1;
  private String LockSkill2;
  private int ImageId;
  private int FullImageId;
  private int Price;
  private int BaseExp;
  private int EvoCost;
  private int RacePacket;
  private int RacePacketRoll;
  private int MaxInDeck;
  private int SeniorPacket;
  private int MasterPacket;
  private int Maze;
  private int Robber;
  private int MagicCard;
  private int Boss;
  private int BossCounter;
  private int FactionCounter;
  private int Glory;
  private int FightMPacket;
  private int[] HpArray;
  private int[] AttackArray;
  private long [] ExpArray;

  public int getCardId() {
    return CardId;
  }

  public String getCardName() {
    return CardName;
  }

  public int getCost() {
    return Cost;
  }

  public int getColor() {
    return Color;
  }

  public int getRace() {
    return Race;
  }

  public int getAttack() {
    return Attack;
  }

  public int getWait() {
    return Wait;
  }

  public String getSkill() {
    return Skill;
  }

  public String getLockSkill1() {
    return LockSkill1;
  }

  public String getLockSkill2() {
    return LockSkill2;
  }

  public int getImageId() {
    return ImageId;
  }

  public int getFullImageId() {
    return FullImageId;
  }

  public int getPrice() {
    return Price;
  }

  public int getBaseExp() {
    return BaseExp;
  }

  public int getEvoCost() {
    return EvoCost;
  }

  public int getRacePacket() {
    return RacePacket;
  }

  public int getRacePacketRoll() {
    return RacePacketRoll;
  }

  public int getMaxInDeck() {
    return MaxInDeck;
  }

  public int getSeniorPacket() {
    return SeniorPacket;
  }

  public int getMasterPacket() {
    return MasterPacket;
  }

  public int getMaze() {
    return Maze;
  }

  public int getRobber() {
    return Robber;
  }

  public int getMagicCard() {
    return MagicCard;
  }

  public int getBoss() {
    return Boss;
  }

  public int getBossCounter() {
    return BossCounter;
  }

  public int getFactionCounter() {
    return FactionCounter;
  }

  public int getGlory() {
    return Glory;
  }

  public int getFightMPacket() {
    return FightMPacket;
  }

  public int[] getHpArray() {
    return HpArray;
  }

  public int[] getAttackArray() {
    return AttackArray;
  }

  public long[] getExpArray() {
    return ExpArray;
  }

  @Override
  public String toString() {
    return "Card " + CardId + " " + CardName;
  }
}
