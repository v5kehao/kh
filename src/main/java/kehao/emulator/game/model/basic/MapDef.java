package kehao.emulator.game.model.basic;

import java.util.List;

public class MapDef {
 private int MapStageId;
 private String Name;
 private int Count;
 private int EveryDayReward;
 private int Rank;
 private int MazeCount;
 private int NeedStar;
 private int Prev;
 private int Next;
 private List<MapStageDef> MapStageDetails;

  public int getMapStageId() {
    return MapStageId;
  }
  public String getName() {
    return Name;
  }

  public int getCount() {
    return Count;
  }

  public int getEveryDayReward() {
    return EveryDayReward;
  }

  public int getRank() {
    return Rank;
  }

  public int getMazeCount() {
    return MazeCount;
  }

  public int getNeedStar() {
    return NeedStar;
  }

  public int getPrev() {
    return Prev;
  }

  public int getNext() {
    return Next;
  }

  public List<MapStageDef> getMapStageDetails() {
    return MapStageDetails;
  }
}
