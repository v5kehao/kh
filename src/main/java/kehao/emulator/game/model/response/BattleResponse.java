package kehao.emulator.game.model.response;

import kehao.emulator.game.model.basic.Battle;

public abstract class BattleResponse<T extends Battle> extends EnergyUsingResponse<T> {
  public static final String InvalidBattleMessage = "没有战斗信息"; // 没有战斗信息!

  public boolean invalidMazeLayer() {
    return message != null && message.contains(InvalidBattleMessage);
  }
}
