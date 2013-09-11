package kehao.emulator.game.model.basic;

public class SkillDef {

  public static final int Type_Assassination = 1;
  public static final int Type_Pollution = 2;
  public static final int Type_CrucialAttack = 3;
  public static final int Type_HolyLight = 4;
  public static final int Type_LifeSteal = 5;
  public static final int Type_TeamLifeSteal = 6;
  public static final int Type_CounterAttack = 7;
  public static final int Type_TeamCounterAttack = 8;
  public static final int Type_Spike = 9; // BossArmor
  public static final int Type_TeamSpike = 10;
  public static final int Type_Trap = 11;
  public static final int Type_Thunder = 12;
  public static final int Type_ChainLightning = 13;
  public static final int Type_Storm = 14;
  public static final int Type_FrostBolt = 15;
  public static final int Type_FrostNova = 16;
  public static final int Type_Blizzard = 17;
  public static final int Type_FireBall = 18;
  public static final int Type_FireWall = 19;
  public static final int Type_FireStorm = 20;
  public static final int Type_DrainLife = 21;
  public static final int Type_Poison = 22;
  public static final int Type_ToxicSmog = 23;
  public static final int Type_ToxicCloud = 24;
  public static final int Type_Block = 25;
  public static final int Type_TeamBlock = 26;
  public static final int Type_Heal = 27;
  public static final int Type_TeamHeal = 28;
  public static final int Type_Snipe = 29; // DoubleTap, TeamSnipe, BossBlade
  public static final int Type_Sweep = 30; // ChainAttack
  public static final int Type_KingdomPower = 31;
  public static final int Type_ForestPower = 32;
  public static final int Type_HellPower = 33;
  public static final int Type_SavagePower = 34;
  public static final int Type_SourcePower = 35;
  public static final int Type_KingdomArmor = 36;
  public static final int Type_ForestArmor = 37;
  public static final int Type_HellArmor = 38;
  public static final int Type_SavageArmor = 39;
  public static final int Type_SourceArmor = 40; // HolyArmor
  public static final int Type_Regeneration = 41;
  public static final int Type_TeamRegeneration = 42;
  public static final int Type_Rebirth = 43; // TeamRebirth
  public static final int Type_Weaken = 44;
  public static final int Type_TeamWeaken = 45;
  public static final int Type_EnergySheild = 46;
  public static final int Type_TeamEnergySheild = 47;
  public static final int Type_Empower = 48;
  public static final int Type_TeamEmpower = 49;
  public static final int Type_Backstab = 50;
  public static final int Type_Explosion = 51;
  public static final int Type_ChainExplosion = 52;
  public static final int Type_Pray = 53;
  public static final int Type_Curse = 54; // Retribution, BossCurse
  public static final int Type_BloodSacrifice = 55;
  public static final int Type_BloodLust = 56;
  public static final int Type_TeamBloodLust = 57;
  public static final int Type_Frenzy = 58;
  public static final int Type_TeamFrenzy = 59;
  public static final int Type_Disease = 60;
  public static final int Type_Plague = 61;
  public static final int Type_Resurrection = 62; // Resurrection_Will, Resurrection_Befall
  public static final int Type_Transmission = 63;
  public static final int Type_SpellReflection = 64;
  public static final int Type_TeamSpellReflection = 65;
  public static final int Type_Withdrawal = 66;
  public static final int Type_Destroy = 67;
  public static final int Type_Evocation = 68;
  public static final int Type_Burn = 69;
  public static final int Type_Inferno = 70;
  public static final int Type_IceArmor = 71;
  public static final int Type_TeamIceArmor = 72;
  public static final int Type_Seal = 73;
  public static final int Type_Lancinate = 74;
  public static final int Type_SpellArmor = 75;
  public static final int Type_TeamSpellArmor = 76;
  public static final int Type_Bedrock = 77;
  public static final int Type_Immune = 78; // BreakLoose
  public static final int Type_FirmStorm_Will = 79;
  public static final int Type_Storm_Will = 80;
  public static final int Type_Blizzard_Will = 81;
  public static final int Type_ToxicCloud_Will = 82;
  public static final int Type_Plague_Will = 83;
  public static final int Type_Heal_Will = 84;
  public static final int Type_TeamHeal_Will = 85;
  public static final int Type_Pray_Will = 86;
  public static final int Type_Curse_Will = 87;
  public static final int Type_TeamWeaken_Will = 88;
  public static final int Type_Inferno_Will = 89;
  public static final int Type_Trap_Will = 90;
  public static final int Type_Guardian = 91;
  public static final int Type_Evasion = 92; // TeamEvasion, Dinner
  public static final int Type_Sacrifice = 93;
  public static final int Type_CriticalHit = 94; // Warpath, Pursue, HeroSlayer, Overdraw, TeamCriticalHit, TeamPursue, TeamWarpath
  public static final int Type_Penetrate = 95; // WeaknessAttack,
  public static final int Type_FirmStorm_Befall = 96;
  public static final int Type_Storm_Befall = 97;
  public static final int Type_Blizzard_Befall = 98;
  public static final int Type_ToxicCloud_Befall = 99;
  public static final int Type_Plague_Befall = 100;
  public static final int Type_Heal_Befall = 101;
  public static final int Type_TeamHeal_Befall = 102;
  public static final int Type_Pray_Befall = 103;
  public static final int Type_Curse_Befall = 104;
  public static final int Type_TeamWeaken_Befall = 105;
  public static final int Type_Inferno_Befall = 106;
  public static final int Type_Trap_Befall = 107;
  public static final int Type_Withdrawal_Befall = 108;
  public static final int Type_Destroy_Befall = 109;
  public static final int Type_Transmission_Befall = 110;
  public static final int Type_Confusion = 111;

  public static final int LanchType_BeforeAttack = 1; // e.g. Pursue
  public static final int LanchType_Attack = 2;
  public static final int LanchType_Attacked = 3;
  public static final int LanchType_Spell = 4; // e.g. Curse
  public static final int LanchType_BeforeAttacked = 5; // e.g. IceArmor
  public static final int LanchType_Passive = 6; // e.g. KingdomPower
  public static final int LanchType_AfterAttack = 7; // e.g. Regeneration
  public static final int LanchType_Death = 8; // e.g. Rebirth
  public static final int LanchType_Will = 9;
  public static final int LanchType_Befall = 10;
  public static final int LanchType_ElementSpellTargeted = 11; // e.g. SpellReflection
  public static final int LanchType_FatalSpellTargeted = 12; // e.g. Rockbed
  public static final int LanchType_AllSpellTargeted = 13; // e.g. Immune
  public static final int LanchType_BeforeHeroAttacked = 14; // e.g. Guardian
  public static final int LanchType_Rune = 15;

  public static final int AffectType_RaceAnnihilate = 1;
  public static final int AffectType_LifeSteal = 2;
  public static final int AffectType_CounterAttack = 3;
  public static final int AffectType_Spike = 4;
  public static final int AffectType_Trap = 5;
  public static final int AffectType_Thunder = 6;
  public static final int AffectType_ChainLightning = 7;
  public static final int AffectType_Storm = 8;
  public static final int AffectType_FrostBolt = 9;
  public static final int AffectType_FrostNova = 10;
  public static final int AffectType_Blizzard = 11;
  public static final int AffectType_FireBall = 12;
  public static final int AffectType_FireWall = 13;
  public static final int AffectType_FireStorm = 14;
  public static final int AffectType_LifeDrain = 15;
  public static final int AffectType_Poison = 16;
  public static final int AffectType_ToxicSmog = 17;
  public static final int AffectType_ToxicCloud = 18;
  public static final int AffectType_Block = 19;
  public static final int AffectType_Heal = 20;
  public static final int AffectType_TeamHeal = 21;
  public static final int AffectType_Snipe = 22;
  public static final int AffectType_Sweep = 23;
  public static final int AffectType_RacePower = 24;
  public static final int AffectType_RaceArmor = 25;
  public static final int AffectType_Regeneration = 26;
  public static final int AffectType_Rebirth = 27;
  public static final int AffectType_Weaken = 28;
  public static final int AffectType_TeamWeaken = 29;
  public static final int AffectType_Backstab = 30;
  public static final int AffectType_Explosion = 31;
  public static final int AffectType_Pray = 32;
  public static final int AffectType_Curse = 33;
  public static final int AffectType_BloodSacrifice = 34;
  public static final int AffectType_Bloodlust = 35;
  public static final int AffectType_Frenzy = 36;
  public static final int AffectType_Disease = 37;
  public static final int AffectType_Plague = 38;
  public static final int AffectType_Resurrection = 39;
  public static final int AffectType_Transimission = 40;
  public static final int AffectType_SpellReflection = 41;
  public static final int AffectType_Withdrawal = 42;
  public static final int AffectType_Destroy = 43;
  public static final int AffectType_Evocation = 44;
  public static final int AffectType_Burn = 45;
  public static final int AffectType_Inferno = 46;
  public static final int AffectType_IceArmor = 47;
  public static final int AffectType_Seal = 48;
  public static final int AffectType_Lancinate = 49;
  public static final int AffectType_SpellArmor = 50;
  public static final int AffectType_Bedrock = 51;
  public static final int AffectType_Immune = 52;
  public static final int AffectType_Will = 53;
  public static final int AffectType_Guardian = 54;
  public static final int AffectType_Evasion = 54;
  public static final int AffectType_Sacrifice = 56;
  public static final int AffectType_CriticalHit = 57;
  public static final int AffectType_Penetrate = 58;
  public static final int AffectType_Befall = 59;
  public static final int AffectType_TeamCriticalHit = 60;
  public static final int AffectType_EnergyShield = 61;
  public static final int AffectType_TeamEnergyShield = 62;
  public static final int AffectType_Empower = 63;
  public static final int AffectType_TeamEmpower = 64;
  public static final int AffectType_Warpath = 65;
  public static final int AffectType_WeaknessAttack = 66;
  public static final int AffectType_Retribution = 67;
  public static final int AffectType_HeroSlayer = 68;
  public static final int AffectType_Confusion = 69;
  public static final int AffectType_ChainAttack = 70;
  public static final int AffectType_BreakLoose = 71;
  public static final int AffectType_Overdraw = 72;
  public static final int AffectType_HolyArmor = 73;

  public static final int SkillCategory_Aggressive = 1;
  public static final int SkillCategory_Spell = 2;
  public static final int SkillCategory_Defensive = 3;
  public static final int SkillCategory_Heal = 4;
  public static final int SkillCategory_Buff = 5;

  private int SkillId;
  private String Name;
  private int Type;
  private int LanchType;
  private int LanchCondition;
  private int LanchConditionValue;
  private int AffectType;
  private int AffectValue;
  private int AffectValue2;
  private int SkillCategory;
  private String Desc;

  public int getSkillId() {
    return SkillId;
  }

  public void setSkillId(int skillId) {
    SkillId = skillId;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public int getType() {
    return Type;
  }

  public void setType(int type) {
    Type = type;
  }

  public int getLanchType() {
    return LanchType;
  }

  public void setLanchType(int lanchType) {
    LanchType = lanchType;
  }

  public int getLanchCondition() {
    return LanchCondition;
  }

  public void setLanchCondition(int lanchCondition) {
    LanchCondition = lanchCondition;
  }

  public int getLanchConditionValue() {
    return LanchConditionValue;
  }

  public void setLanchConditionValue(int lanchConditionValue) {
    LanchConditionValue = lanchConditionValue;
  }

  public int getAffectType() {
    return AffectType;
  }

  public void setAffectType(int affectType) {
    AffectType = affectType;
  }

  public int getAffectValue() {
    return AffectValue;
  }

  public void setAffectValue(int affectValue) {
    AffectValue = affectValue;
  }

  public int getAffectValue2() {
    return AffectValue2;
  }

  public void setAffectValue2(int affectValue2) {
    AffectValue2 = affectValue2;
  }

  public int getSkillCategory() {
    return SkillCategory;
  }

  public void setSkillCategory(int skillCategory) {
    SkillCategory = skillCategory;
  }

  public String getDesc() {
    return Desc;
  }

  public void setDesc(String desc) {
    Desc = desc;
  }
}
