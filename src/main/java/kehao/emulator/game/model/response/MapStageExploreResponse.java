package kehao.emulator.game.model.response;

import kehao.emulator.game.model.basic.Explore;

public class MapStageExploreResponse extends EnergyUsingResponse<Explore> {

  public static final String StageNotAvailableMessage = "关卡尚未开启";  // 关卡尚未开启!

  public boolean stageNotAvailable() {
    return message != null && message.contains(StageNotAvailableMessage);
  }
}
