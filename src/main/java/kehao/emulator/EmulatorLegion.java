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
public class EmulatorLegion {

  @Autowired
  private UnknownErrorHandler unknownErrorHandler;
  @Autowired
  private EmulatorCore core;

  public Tech getTech(String username) throws ServerNotAvailableException,
                                                WrongCredentialException {
    LegionGetTechResponse response = core.gameDoAction(username, "legion.php", "GetTech", null, LegionGetTechResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public UserLegion getUserLegion(String username) throws ServerNotAvailableException,
                                                            WrongCredentialException {
    LegionGetUserLegionResponse response = core.gameDoAction(username, "legion.php", "GetUserLegion", null, LegionGetUserLegionResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public Legions getLegions(String username) throws ServerNotAvailableException,
                                                      WrongCredentialException {
    LegionGetLegionsResponse response = core.gameDoAction(username, "legion.php", "GetLegions", null, LegionGetLegionsResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public Members getMembers(String username) throws ServerNotAvailableException,
                                                      WrongCredentialException {
    LegionGetMemberResponse response = core.gameDoAction(username, "legion.php", "GetMember", null, LegionGetMemberResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public boolean donate(String username, int type, int tech, int coins) throws ServerNotAvailableException,
                                                                                 WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("Type", Integer.toString(type));
    params.put("TechType", Integer.toString(tech));
    params.put("Coins", Integer.toString(coins));
    LegionGetMemberResponse response = core.gameDoAction(username, "legion.php", "Donate", params, LegionGetMemberResponse.class);
    if(response.badRequest()) {
      return false;
    }
    return true;
  }

}
