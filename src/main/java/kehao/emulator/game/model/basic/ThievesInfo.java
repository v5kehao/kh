package kehao.emulator.game.model.basic;

public class ThievesInfo {
  private long Uid;
  private String NickName;
  private int Avatar;
  private int Sex;
  private long Time;
  private int Status;  // 1 = active, 2 = finished
  private long[] Attackers;
  private long[] Awards;
  private int HPCount;
  private int HPCurrent;
  private int Type;
  private long UserThievesId;
  private int Round;
  private int MaxAttack;
  private long MaxAttackUid;
  private String MaxAttackName;
  private long LastAttackerUid;
  private String LastAttackerName;
  private long FleeTime;

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

  public long getTime() {
    return Time;
  }

  public int getStatus() {
    return Status;
  }

  public long[] getAttackers() {
    return Attackers;
  }

  public long[] getAwards() {
    return Awards;
  }

  public int getHPCount() {
    return HPCount;
  }

  public int getHPCurrent() {
    return HPCurrent;
  }

  public int getType() {
    return Type;
  }

  public long getUserThievesId() {
    return UserThievesId;
  }

  public int getRound() {
    return Round;
  }

  public int getMaxAttack() {
    return MaxAttack;
  }

  public long getMaxAttackUid() {
    return MaxAttackUid;
  }

  public String getMaxAttackName() {
    return MaxAttackName;
  }

  public long getLastAttackerUid() {
    return LastAttackerUid;
  }

  public String getLastAttackerName() {
    return LastAttackerName;
  }

  public long getFleeTime() {
    return FleeTime;
  }
}
