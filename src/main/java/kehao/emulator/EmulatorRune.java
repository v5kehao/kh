package kehao.emulator;

import kehao.emulator.game.model.basic.Runes;
import kehao.emulator.game.model.basic.UserRunes;
import kehao.emulator.game.model.response.RuneGetAllRuneResponse;
import kehao.emulator.game.model.response.RuneGetUserRunesResponse;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorRune {

  @Autowired
  private EmulatorCore core;
  @Autowired
  private UnknownErrorHandler unknownErrorHandler;

  public Runes getAllRune(String username) throws ServerNotAvailableException,
                                                    WrongCredentialException {
    RuneGetAllRuneResponse response = core.gameDoAction(username, "rune.php", "GetAllRune", null, RuneGetAllRuneResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public UserRunes getUserRunes(String username) throws ServerNotAvailableException,
                                                          WrongCredentialException {
    RuneGetUserRunesResponse response = core.gameDoAction(username, "rune.php", "GetUserRunes", null, RuneGetUserRunesResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

}
