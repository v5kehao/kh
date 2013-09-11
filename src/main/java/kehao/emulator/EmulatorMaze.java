package kehao.emulator;

import java.util.LinkedHashMap;
import java.util.Map;

import kehao.emulator.game.model.basic.*;
import kehao.emulator.game.model.response.*;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import kehao.exception.maze.InsufficientEnergyException;
import kehao.exception.maze.InvalidMazeLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorMaze {
  @Autowired
  private UnknownErrorHandler unknownErrorHandler;
  @Autowired
  private EmulatorCore core;

  public BattleNormal battle(String username, int mazeId, int layer, int itemIndex) throws InsufficientEnergyException,
                                                                                             InvalidMazeLayerException,
                                                                                             ServerNotAvailableException,
                                                                                             WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<String, String>();
    params.put("manual", Integer.toString(0));
    params.put("MapStageId", Integer.toString(mazeId));
    params.put("Layer", Integer.toString(layer));
    params.put("ItemIndex", Integer.toString(itemIndex));
    MazeBattleResponse response = core.gameDoAction(username, "maze.php", "Battle", params, MazeBattleResponse.class);
    if(response.badRequest()) {
      if(response.noEnergy()) {
        throw new InsufficientEnergyException();
      }
      if(response.invalidMazeLayer()) {
        throw new InvalidMazeLayerException();
      }
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public MazeInfo info(String username, int mazeId, int layer) throws ServerNotAvailableException,
                                                                        WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<String, String>();
    params.put("MapStageId", Integer.toString(mazeId));
    params.put("Layer", Integer.toString(layer));
    MazeInfoResponse response = core.gameDoAction(username, "maze.php", "Info", params, MazeInfoResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public MazeStatus show(String username, int mazeId) throws ServerNotAvailableException,
                                                               WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("MapStageId", Integer.toString(mazeId));
    MazeShowResponse response = core.gameDoAction(username, "maze.php", "Show", params, MazeShowResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public boolean reset(String username, int mazeId) throws ServerNotAvailableException,
                                                             WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("MapStageId", Integer.toString(mazeId));
    MazeResetResponse response = core.gameDoAction(username, "maze.php", "Reset", params, MazeResetResponse.class);
    if(response.badRequest()) {
      return false;
    }
    return true;
  }

}
