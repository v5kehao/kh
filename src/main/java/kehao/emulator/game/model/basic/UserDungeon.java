package kehao.emulator.game.model.basic;

public class UserDungeon {
  private long Uid;
  private int CurrentLayer;
  private int MaxFinishLayer;
  private int Resurrection;
  private int BuyTimes;
  private int Anger;
  private int Status;
  private int FinishedBoss;
  private int RaidsStatus;
  private int RaidsLayer;

  public long getUid() {
    return Uid;
  }

  public int getCurrentLayer() {
    return CurrentLayer;
  }

  public int getMaxFinishLayer() {
    return MaxFinishLayer;
  }

  public int getResurrection() {
    return Resurrection;
  }

  public int getBuyTimes() {
    return BuyTimes;
  }

  public int getAnger() {
    return Anger;
  }

  public int getStatus() {
    return Status;
  }

  public int getFinishedBoss() {
    return FinishedBoss;
  }

  public int getRaidsStatus() {
    return RaidsStatus;
  }

  public int getRaidsLayer() {
    return RaidsLayer;
  }
}
