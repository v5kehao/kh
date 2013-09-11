package kehao.emulator.game.model.response;

import kehao.emulator.game.model.basic.EnergyUser;

public abstract class EnergyUsingResponse<T extends EnergyUser> extends GameData<T> {
  public static final String NoEnergyString = "行动力不足"; // 行动力不足!每10分钟可恢复1点!您也可以使用晶钻购买行动力哦!

  public boolean noEnergy() {
    return type == 4 || (message != null && message.contains(NoEnergyString));
  }
}
