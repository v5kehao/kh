package kehao.emulator;

import java.util.LinkedHashMap;
import java.util.Map;

import kehao.emulator.game.model.response.FEnergyGetFEnergyResponse;
import kehao.emulator.game.model.response.FEnergySendFEnergyResponse;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorFEnergy {

  @Autowired
  private EmulatorCore core;
  @Autowired
  private UnknownErrorHandler unknownErrorHandler;

  public boolean sendFEnergy(String username, long fid) throws ServerNotAvailableException,
                                                                 WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("Fid", Long.toString(fid));
    FEnergySendFEnergyResponse response = core.gameDoAction(username, "fenergy.php", "SendFEnergy", params, FEnergySendFEnergyResponse.class);
    if(response.badRequest()) {
      if(response.energySendMax()) {
        return false;
      }
      if(response.energyAlreadySent()) {
        return false;
      }
      unknownErrorHandler.print(username, response.getMessage());
    }
    return true;
  }

  public boolean getFEnergy(String username, long fid) throws ServerNotAvailableException,
                                                                WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<String, String>();
    params.put("Fid", Long.toString(fid));
    FEnergyGetFEnergyResponse response = core.gameDoAction(username, "fenergy.php", "GetFEnergy", params, FEnergyGetFEnergyResponse.class);
    if(response.badRequest()) {
      if(response.energyGetMax()) {
        return false;
      }
      if(response.energyOverMax()) {
        return false;
      }
      unknownErrorHandler.print(username, response.getMessage());
    }
    return true;
  }


}
