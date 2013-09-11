package kehao.service;

import java.util.*;

import kehao.emulator.EmulatorArena;
import kehao.emulator.game.model.basic.BattleNormal;
import kehao.emulator.game.model.basic.Thieves;
import kehao.emulator.game.model.basic.ThievesInfo;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import kehao.model.ThiefSetting;
import kehao.util.AppendableReport;
import kehao.util.DateStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThiefService extends FunctionalServiceProvider<ThiefSetting> {
  private EmulatorArena arena;
  private AdminService adminService;

  @Autowired
  public ThiefService(LockingService locker, EmulatorArena arena, AdminService adminService, AssetsService assetsService) {
    super(locker, assetsService);
    this.arena = arena;
    this.adminService = adminService;
  }

  @Override
  protected void run(String username, ThiefSetting setting, AppendableReport report) throws ServerNotAvailableException,
                                                                                                WrongCredentialException {
    String src = src(username);
    ThievesInfo thief = findThief(username, setting.isFavourSelf(), setting.isFavourElite(), setting.isFavourLowHp(), setting.getMinThreshold(), setting.getHpThreshold(), report);
    if(thief != null) {
      fightThief(username, thief, report);
    }
  }

  @Override
  protected String printConfig(String username, ThiefSetting setting) {
    return "";
  }

  private ThievesInfo findThief(String username, boolean favourSelf, boolean favourElite, boolean favourLowHp, int minThreshold, int hpThreshold, AppendableReport report) throws ServerNotAvailableException, WrongCredentialException {
    String src = src(username);
    Thieves thieves = arena.getThieves(username);
    if(thieves.getCountdown() > 0) {
      report.append(src, username + "打贼冷却时间剩余" + DateStringUtils.secondsToString(thieves.getCountdown()) + "，放弃打贼");
      return null;
    }
    long uid = adminService.user(username).getUid();
    List<ThievesInfo> thiefList = thieves.getThieves();
    List<ThievesInfo> pending = new ArrayList<>();
    for(ThievesInfo thief : thiefList) {
      if(thief.getFleeTime() <= 0 || thief.getHPCurrent() <= 0) continue;
      if(favourSelf && uid == thief.getUid()) {
        report.append(src, "发现" + thief.getNickName() + "通缉的盗贼");
        return thief;
      }
      if(thief.getFleeTime() < minThreshold * 60 && thief.getHPCurrent() > hpThreshold) {
        report.append(src, "发现" + thief.getNickName() + "通缉的盗贼，生命值高于" + hpThreshold + "且剩余时间低于" + minThreshold + "分钟，放弃该盗贼");
        return thief;
      }
      if(favourElite && thief.getType() == 2) {
        report.append(src, "发现" + thief.getNickName() + "通缉的精英盗贼");
        return thief;
      }
      pending.add(thief);
    }
    if(pending.isEmpty()) {
      report.append(src, "未发现任何符合条件的盗贼，放弃打贼");
      return null;
    }
    report.append(src, "共发现" + pending.size() + "符合条件的盗贼");
    if(favourLowHp) {
      report.append(src, "根据剩余生命值选择对战目标");
      Collections.sort(pending, new Comparator<ThievesInfo>() {
        @Override
        public int compare(ThievesInfo t1, ThievesInfo t2) {
          return Integer.compare(t1.getHPCurrent(), t2.getHPCurrent());
        }
      });
    }
    ThievesInfo ret = pending.get(0);
    report.append(src, "选择" + ret.getNickName() + "通缉的盗贼（生命值：" + ret.getHPCurrent() + "/" + ret.getHPCount() + "，剩余时间：" + DateStringUtils.secondsToString((int) ret.getFleeTime()) + "）");
    return pending.get(0);
  }

  public void fightThief(String username, ThievesInfo theif, AppendableReport report)  throws ServerNotAvailableException, WrongCredentialException {
    String src = src(username);
    BattleNormal battle = arena.thievesFight(username, theif.getUserThievesId());
    report.append(src, parseBattle(username, battle));
  }

}
