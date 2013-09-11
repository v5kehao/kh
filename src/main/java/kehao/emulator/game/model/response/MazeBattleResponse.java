package kehao.emulator.game.model.response;

import kehao.emulator.game.model.basic.BattleNormal;

public class MazeBattleResponse extends BattleResponse<BattleNormal> {
  public static final String InvalidMazeLayer = "迷宫尚未开启"; // 迷宫尚未开启.

  public boolean invalidMazeLayer() {
    return message != null && message.contains(InvalidMazeLayer);
  }
}
