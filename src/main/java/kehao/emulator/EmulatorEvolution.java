package kehao.emulator;

import java.util.LinkedHashMap;
import java.util.Map;

import kehao.emulator.game.model.basic.EvolutionResult;
import kehao.emulator.game.model.response.EvolutionPreviewResponse;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorEvolution {

  @Autowired
  private EmulatorCore core;
  @Autowired
  private UnknownErrorHandler unknownErrorHandler;

  public EvolutionResult preview(String username, int type, long target, long source) throws ServerNotAvailableException,
                                                                                               WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("Type", Integer.toString(type));
    params.put("UserCardId1", Long.toString(target));
    params.put("UserCardId2", Long.toString(source));
    EvolutionPreviewResponse response = core.gameDoAction(username, "evolution.php", "Preview", params, EvolutionPreviewResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public EvolutionResult evolve(String username, long target, long source) throws ServerNotAvailableException,
                                                                                    WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("UserCardId1", Long.toString(target));
    params.put("UserCardId2", Long.toString(source));
    EvolutionPreviewResponse response = core.gameDoAction(username, "evolution.php", null, params, EvolutionPreviewResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }
}
