package kehao.emulator.game.model.response;

public class ShopBuyResponse extends GameData<String> {
  public static final String NoCurrencyMessage = "没有";  // 您没有剩余的魔幻券哦!

  public boolean noCurrency() {
    return message != null && message.contains(NoCurrencyMessage);
  }
}
