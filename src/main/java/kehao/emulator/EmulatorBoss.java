package kehao.emulator;

import kehao.emulator.game.model.basic.*;
import kehao.emulator.game.model.response.*;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import kehao.exception.boss.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorBoss {
  @Autowired
  private EmulatorCore core;
  @Autowired
  private UnknownErrorHandler unknownErrorHandler;

  public BossUpdate getBoss(String username) throws ServerNotAvailableException,
                                                      WrongCredentialException {
    BossGetBossResponse response = core.gameDoAction(username, "boss.php", "GetBoss", null, BossGetBossResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public BossFight fight(String username) throws ServerNotAvailableException,
                                                   WrongCredentialException,
                                                   BossAlreadyInQueueException,
                                                   BossDownException,
                                                   BossUnavailableException,
                                                   BossCoolingDownException {
    BossFightResponse response = core.gameDoAction(username, "boss.php", "Fight", null, BossFightResponse.class);
    if(response.badRequest()) {
      if(response.isAlreadyInQueue()) {
        throw new BossAlreadyInQueueException();
      }
      if(response.isBossDown()) {
        throw new BossDownException();
      }
      if(response.isBossUnavailable()) {
        throw new BossUnavailableException();
      }
      if(response.isCoolingDown()) {
        throw new BossCoolingDownException();
      }
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public BattleNormal getFightData(String username) throws ServerNotAvailableException,
                                                             WrongCredentialException {
    BossGetFightDataResponse response = core.gameDoAction(username, "boss.php", "GetFightData", null, BossGetFightDataResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public BossRank getRanks(String username) throws ServerNotAvailableException,
                                                     WrongCredentialException {
    BossGetRanksResponse response = core.gameDoAction(username, "boss.php", "GetRanks", null, BossGetRanksResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public Object gameBossBuyTime(String username) throws ServerNotAvailableException,
                                                          WrongCredentialException {
    BossBuyTimeResponse response = core.gameDoAction(username, "boss.php", "BuyTime", null, BossBuyTimeResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public BossStatus getStatus(String username) throws ServerNotAvailableException,
                                                        WrongCredentialException {
    BossGetStatusResponse response = core.gameDoAction(username, "boss.php", "GetStatus", null, BossGetStatusResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

}
