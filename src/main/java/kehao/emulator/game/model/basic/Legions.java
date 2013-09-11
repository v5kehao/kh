package kehao.emulator.game.model.basic;

import java.util.List;

public class Legions {
  private Legion MyLegion;
  private Member MyInfo;
  private List<Legion> LegionInfos;
  private int Count;

  public Legion getMyLegion() {
    return MyLegion;
  }

  public Member getMyInfo() {
    return MyInfo;
  }

  public List<Legion> getLegionInfos() {
    return LegionInfos;
  }
  public int getCount() {
    return Count;
  }
}
