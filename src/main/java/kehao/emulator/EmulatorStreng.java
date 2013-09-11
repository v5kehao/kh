package kehao.emulator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kehao.emulator.game.model.basic.Streng;
import kehao.emulator.game.model.response.StrengCardResponse;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorStreng {

  @Autowired
  private EmulatorCore core;
  @Autowired
  private UnknownErrorHandler unknownErrorHandler;


  public Streng card(String username, long targetUserCardId, List<Long> sourceUserCardIds) throws ServerNotAvailableException,
                                                                                                    WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    StringBuilder sb = new StringBuilder();
    for(long sourceId : sourceUserCardIds) {
      if(sb.length() > 0) {
        sb.append("_");
      }
      sb.append(sourceId);
    }
    params.put("UserCardId2", sb.toString());
    params.put("UserCardId1", Long.toString(targetUserCardId));
    StrengCardResponse response = core.gameDoAction(username, "streng.php", "Card", params, StrengCardResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

}
