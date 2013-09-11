package kehao.emulator.game.model.basic;

public class MazeInfo {

  public static final int EnergyExpend = 2;

  public static final int Monster = 3;
  public static final int DownStair = 4;
  public static final int UpStair = 5;
  public static final int Box = 2;
  public static final int Empty = 1;

  private String Name;
  private int BoxNum;
  private int MonsterNum;
  private int RemainBoxNum;
  private int RemainMonsterNum;
  private int Layer;
  private int TotalLayer;
  private MazeMap Map;

  public boolean isCleared() {
    return Map.isIsFinish();
  }

  public int[] getItems() {
    return Map.getItems();
  }

  public String getName() {
    return Name;
  }
  public int getBoxNum() {
    return BoxNum;
  }

  public int getMonsterNum() {
    return MonsterNum;
  }

  public int getRemainBoxNum() {
    return RemainBoxNum;
  }

  public int getRemainMonsterNum() {
    return RemainMonsterNum;
  }

  public int getLayer() {
    return Layer;
  }

  public int getTotalLayer() {
    return TotalLayer;
  }

  public MazeMap getMap() {
    return Map;
  }

  public class MazeMap {
    private boolean IsFinish;
    private int[] WallRows;
    private int[] WallCols;
    private int[] Items;

    public boolean isIsFinish() {
      return IsFinish;
    }

    public int[] getWallRows() {
      return WallRows;
    }

    public int[] getWallCols() {
      return WallCols;
    }

    public int[] getItems() {
      return Items;
    }
  }
}
