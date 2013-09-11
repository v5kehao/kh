package kehao.emulator;

import kehao.emulator.game.model.basic.LegionAttackInfo;
import kehao.emulator.game.model.response.LegionAttackInfoResponse;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorLegionAttack {

  @Autowired
  private UnknownErrorHandler unknownErrorHandler;
  @Autowired
  private EmulatorCore core;

  public LegionAttackInfo info(String username) throws ServerNotAvailableException,
                                                         WrongCredentialException {
    LegionAttackInfoResponse response = core.gameDoAction(username, "legionattack.php", "info", null, LegionAttackInfoResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }
}
