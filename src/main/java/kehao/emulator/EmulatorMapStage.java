package kehao.emulator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;

import kehao.emulator.game.model.basic.*;
import kehao.emulator.game.model.response.*;
import kehao.emulator.model.GameVersion;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorMapStage {

  @Autowired
  private GameVersion gameVersion;
  @Autowired
  private EmulatorCore core;
  @Autowired
  private UnknownErrorHandler unknownErrorHandler;

  public SortedMap<Integer, UserMapStage> getUserMapStages(String username) throws ServerNotAvailableException,
                                                                                     WrongCredentialException {
    MapStageGetUserMapStagesResponse response = core.gameDoAction(username, "mapstage.php", "GetUserMapStages", null, MapStageGetUserMapStagesResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public BattleMap editUserMapStages(String username, int mapStageDetailId) throws ServerNotAvailableException,
                                                                                     WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("MapStageDetailId", Integer.toString(mapStageDetailId));
    params.put("isManual", Integer.toString(0));
    MapStageEditUserMapStagesResponse response = core.gameDoAction(username, "mapstage.php", "EditUserMapStages", params, MapStageEditUserMapStagesResponse.class);
    if(response.badRequest()) {
      if(response.noEnergy()) {
        return null;
      }
      unknownErrorHandler.print(username, response.getMessage());
    }

    return response.getData();
  }

  public boolean awardClear(String username, int mapStageId) throws ServerNotAvailableException,
                                                                      WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("MapStageId", Integer.toString(mapStageId));
    MapStageAwardClearResponse response = core.gameDoAction(username, "mapstage.php", "AwardClear", params, MapStageAwardClearResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return true;
  }

  public Explore explore(String username, int mapStageDetailId) throws ServerNotAvailableException,
                                                                         WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("MapStageDetailId", Integer.toString(mapStageDetailId));
    MapStageExploreResponse response = core.gameDoAction(username, "mapstage.php", "Explore", params, MapStageExploreResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public AllMap getMapStageAll(String username) throws ServerNotAvailableException,
                                                         WrongCredentialException {
    MapStageGetMapStageAllResponse response = core.gameDoAction(username, "mapstage.php", "GetMapStageALL&stageNum=" + gameVersion.getMapMax(), null, MapStageGetMapStageAllResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }
}
