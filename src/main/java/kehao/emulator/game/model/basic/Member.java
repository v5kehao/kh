package kehao.emulator.game.model.basic;

public class Member {
  private long Uid;
  private int LegionId;
  private String JoinTime;
  private long Contribute;
  private int duty;
  private long Honor;

  public long getUid() {
    return Uid;
  }

  public int getLegionId() {
    return LegionId;
  }

  public String getJoinTime() {
    return JoinTime;
  }

  public long getContribute() {
    return Contribute;
  }

  public int getDuty() {
    return duty;
  }

  public long getHonor() {
    return Honor;
  }
}
