package kehao.emulator.game.model.basic;

import java.util.List;

public class LegionAttackInfo {

  private LegionAttackUInfo uinfo;
  private List<LegionAttackDetail> info;
  private List<LegionAttackNext> next;

  public LegionAttackUInfo getUinfo() {
    return uinfo;
  }

  public List<LegionAttackDetail> getInfo() {
    return info;
  }

  public List<LegionAttackNext> getNext() {
    return next;
  }
}
