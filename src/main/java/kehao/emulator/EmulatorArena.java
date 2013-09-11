package kehao.emulator;

import java.util.LinkedHashMap;
import java.util.Map;

import kehao.emulator.game.model.basic.ArenaCompetitors;
import kehao.emulator.game.model.basic.BattleNormal;
import kehao.emulator.game.model.basic.Thieves;
import kehao.emulator.game.model.response.*;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorArena {

  @Autowired
  private UnknownErrorHandler unknownErrorHandler;
  @Autowired
  private EmulatorCore core;

  public ArenaCompetitors getCompetitors(String username) throws ServerNotAvailableException,
                                                                   WrongCredentialException {
    ArenaGetCompetitorsResponse response = core.gameDoAction(username, "arena.php", "GetCompetitors", null, ArenaGetCompetitorsResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    ArenaCompetitors competitors = response.getData();
    return competitors;
  }

  public BattleNormal freeFight(String username, long competitor, boolean forChip) throws ServerNotAvailableException,
                                                                                            WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    if(!forChip) {
      params.put("NoChip", Integer.toString(1));
    }
    params.put("isManual", Integer.toString(0));
    params.put("competitor", Long.toString(competitor));
    ArenaFreeFightResponse response = core.gameDoAction(username, "arena.php", "FreeFight", params, ArenaFreeFightResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public BattleNormal rankFight(String username, int rank) throws ServerNotAvailableException,
                                                                    WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("CompetitorRank", Integer.toString(rank));
    ArenaFreeFightResponse response = core.gameDoAction(username, "arena.php", "RankFight", params, ArenaFreeFightResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public Thieves getThieves(String username) throws ServerNotAvailableException,
                                                      WrongCredentialException {
    ArenaGetThievesResponse response = core.gameDoAction(username, "arena.php", "GetThieves", null, ArenaGetThievesResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public BattleNormal thievesFight(String username, long id) throws ServerNotAvailableException,
                                                                      WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("UserThievesId", Long.toString(id));
    ArenaThievesFightResponse response = core.gameDoAction(username, "arena.php", "ThievesFight", params, ArenaThievesFightResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }


}
