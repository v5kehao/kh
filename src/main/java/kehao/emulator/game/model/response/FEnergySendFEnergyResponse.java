package kehao.emulator.game.model.response;

public class FEnergySendFEnergyResponse extends GameData {

  public static final String EnergySendMaxMessage = "今日赠送达到上限"; // 今日赠送达到上限！
  public static final String EnergyAlreadySentMessage = "今日已经赠送过该好友"; // 今日已经赠送过该好友！

  public boolean energySendMax() {
    return message != null && message.contains(EnergySendMaxMessage);
  }

  public boolean energyAlreadySent() {
    return message != null && message.contains(EnergyAlreadySentMessage);
  }
}
