package kehao.emulator.game.model.basic;

public class BattleNormal extends Battle<BattleNormalExtData> {
  public boolean mazeClear() {
    return getExtData().getClear().getIsClear() != 0;
  }
}
