package kehao.emulator;

import java.util.LinkedHashMap;
import java.util.Map;

import kehao.emulator.game.model.basic.*;
import kehao.emulator.game.model.response.*;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorMelee {
  @Autowired
  private UnknownErrorHandler unknownErrorHandler;
  @Autowired
  private EmulatorCore core;

  public boolean setCardGroup(String username, int type, int prizeCardId, int... otherCardId) throws ServerNotAvailableException,
                                                                                                       WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    StringBuilder sb = new StringBuilder();
    for(int id : otherCardId) {
      if(sb.length() > 0) {
        sb.append('_');
      }
      sb.append(id);
    }
    params.put("PrizeCardId", Integer.toString(prizeCardId));
    params.put("type", Integer.toString(type));
    params.put("OtherCardId", sb.toString());
    MeleeSetCardGroupResponse response = core.gameDoAction(username, "melee.php", "SetCardGroup", params, MeleeSetCardGroupResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return true;
  }

  public MeleeInfo getInfo(String username) throws ServerNotAvailableException,
                                                     WrongCredentialException {
    MeleeGetInfoResponse response = core.gameDoAction(username, "melee.php", "GetInfo", null, MeleeGetInfoResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    MeleeInfo info = response.getData();
    return info;
  }

  public MeleeApplyResult apply(String username, int type) throws ServerNotAvailableException,
                                                                    WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("type", Integer.toString(type));
    MeleeApplyResponse response = core.gameDoAction(username, "melee.php", "Apply", params, MeleeApplyResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public MeleeCardGroup getCardGroup(String username, long uid, int type) throws ServerNotAvailableException,
                                                                                   WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("type", Integer.toString(type));
    params.put("Uid", Long.toString(uid));
    MeleeGetCardGroupResponse response = core.gameDoAction(username, "melee.php", "GetCardGroup", params, MeleeGetCardGroupResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }
}
