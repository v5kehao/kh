package kehao.emulator;

import java.util.SortedMap;

import kehao.emulator.game.model.basic.Chip;
import kehao.emulator.game.model.response.ChipGetUserChipResponse;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorChip {

  @Autowired
  private EmulatorCore core;
  @Autowired
  private UnknownErrorHandler unknownErrorHandler;

  public SortedMap<Integer, Chip> getUserChip(String username) throws ServerNotAvailableException,
                                                                        WrongCredentialException {
    ChipGetUserChipResponse response = core.gameDoAction(username, "chip.php", "GetUserChip", null, ChipGetUserChipResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }
}
