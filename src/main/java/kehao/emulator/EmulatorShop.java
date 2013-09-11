package kehao.emulator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kehao.emulator.game.model.basic.Goods;
import kehao.emulator.game.model.response.ShopBuyResponse;
import kehao.emulator.game.model.response.ShopGetGoodsResponse;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import kehao.exception.shop.InsufficientCurrencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorShop {

  @Autowired
  private UnknownErrorHandler unknownErrorHandler;
  @Autowired
  private EmulatorCore core;



  public List<Goods> getGoods(String username) throws ServerNotAvailableException,
                                                        WrongCredentialException {
    ShopGetGoodsResponse response = core.gameDoAction(username, "shop.php", "GetGoods", null, ShopGetGoodsResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public String buy(String username, int goodsId) throws InsufficientCurrencyException,
                                                           ServerNotAvailableException,
                                                           WrongCredentialException {
    Map<String, String> paramMap = new LinkedHashMap<>();
    paramMap.put("GoodsId", Integer.toString(goodsId));
    ShopBuyResponse response = core.gameDoAction(username, "shop.php", "Buy", paramMap, ShopBuyResponse.class);
    if(response.badRequest()) {
      if(response.noCurrency()) {
        throw new InsufficientCurrencyException();
      } else {
        unknownErrorHandler.print(username, response.getMessage());
      }
    }
    return response.getData();
  }

}
