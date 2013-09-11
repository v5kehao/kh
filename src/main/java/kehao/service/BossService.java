package kehao.service;

import kehao.emulator.EmulatorBoss;
import kehao.emulator.game.model.basic.BattleNormal;
import kehao.emulator.game.model.basic.BossFight;
import kehao.emulator.game.model.basic.BossUpdate;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import kehao.exception.boss.*;
import kehao.model.BossSetting;
import kehao.util.AppendableReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BossService extends FunctionalServiceProvider<BossSetting> {

  private EmulatorBoss boss;

  @Autowired
  public BossService(LockingService locker, EmulatorBoss boss, AssetsService assetsService) {
    super(locker, assetsService);
    this.boss = boss;
  }

  @Override
  protected void run(String username, BossSetting setting, AppendableReport report) throws ServerNotAvailableException, WrongCredentialException {
    String src = src(username);
    cycleBoss(username, setting.isShowBattle(), setting.isShowRank(), report);
    report.finish(src);
  }

  @Override
  protected String printConfig(String username, BossSetting setting) {
    return "";
  }

  synchronized private void cycleBoss(String username, boolean showBattle, boolean showRank, AppendableReport report) throws ServerNotAvailableException, WrongCredentialException {
    String src = src(username);
    BossUpdate bossUpdate = null;
    do {
      try {
        BossFight bossFight = boss.fight(username);
        if(bossFight != null) {
          if(showBattle) {
            BattleNormal battle = boss.getFightData(username);
            report.append(src, parseBattle(username, battle));
          }
          if(showRank) {
            showRank(username, report);
          }
          int interval = bossFight.getCanFightTime();
          report.append(src, "与魔神对战冷却时间剩余" + interval + "秒");
          try {
            wait(interval * 1000);
          } catch(InterruptedException e) {
            e.printStackTrace();
          }
        }
      } catch(BossAlreadyInQueueException | BossCoolingDownException e) {
        bossUpdate = boss.getBoss(username);
        int interval = bossUpdate.getCanFightTime();
        report.append(src, "与魔神对战冷却时间剩余" + interval + "秒");
        try {
          wait(interval * 1000);
        } catch(InterruptedException ie) {
          e.printStackTrace();
        }
      } catch(BossDownException e) {
        report.append(src, "魔神已经被击败");
        showRank(username, report);
        return;
      } catch(BossUnavailableException e) {
        report.append(src, "魔神尚未出现");
        return;
      }
    } while(bossUpdate == null || (bossUpdate.getBoss().getBossCurrentHp() > 0 && bossUpdate.getBossFleeTime() > 0));
  }

  public void showRank(String username, AppendableReport report) {
    String src = src(username);
    try {
      int honor = boss.getBoss(username).getMyHonor();
      if(honor != 0) {
        int rank = boss.getRanks(username).getRank();
        report.append(src, "当前功勋为" + honor + "排名" + rank);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
