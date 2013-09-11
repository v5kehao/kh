package kehao.emulator.game.model.basic;

public class UserMapStage implements Comparable<UserMapStage>{
  private long Uid;
  private int MapStageDetailId;
  private int Type; // 1 = normal, 2 = boss, 0 = secret, 3 = maze
  private int MapStageId;
  private int FinishedStage;
  private String LastFinishedTime;
  private long CounterAttackTime;

  public boolean isCorrupted() {
    return CounterAttackTime != 0;
  }

  public long getUid() {
    return Uid;
  }

  public int getMapStageDetailId() {
    return MapStageDetailId;
  }

  public int getType() {
    return Type;
  }

  public int getMapStageId() {
    return MapStageId;
  }

  public int getFinishedStage() {
    return FinishedStage;
  }

  public String getLastFinishedTime() {
    return LastFinishedTime;
  }

  public long getCounterAttackTime() {
    return CounterAttackTime;
  }

  @Override
  public int compareTo(UserMapStage o) {
    return Integer.compare(getMapStageDetailId(), o.getMapStageDetailId());
  }
}
