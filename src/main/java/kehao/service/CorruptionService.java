package kehao.service;

import java.util.*;

import kehao.emulator.EmulatorMapStage;
import kehao.emulator.EmulatorUser;
import kehao.emulator.game.model.basic.BattleMap;
import kehao.emulator.game.model.basic.UserInfo;
import kehao.emulator.game.model.basic.UserMapStage;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.UserNotAvailableException;
import kehao.exception.WrongCredentialException;
import kehao.model.CorruptionSetting;
import kehao.util.AppendableReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorruptionService extends FunctionalServiceProvider<CorruptionSetting> {

  private EmulatorMapStage map;
  private EmulatorUser user;

  @Autowired
  public CorruptionService(LockingService locker, EmulatorMapStage map, EmulatorUser user, AssetsService assetsService) {
    super(locker, assetsService);
    this.map = map;
    this.user = user;
  }

  @Override
  protected void run(String username, CorruptionSetting setting, AppendableReport report) throws UserNotAvailableException,
                                                                                                     ServerNotAvailableException,
                                                                                                     WrongCredentialException {
    String src = src(username);
    UserInfo info = user.getUserInfo(username);
    String nick = info.getNickName();
    int energy = info.getEnergy();
    if(energy <= setting.getEnergy()) {
      report.append(src, nick + "现有体力" + energy + "低于启动条件（≤" + setting.getEnergy() + "），放弃清除反攻");
    } else {
      Set<Integer> ignoredMaps = new HashSet<>();
      if(setting.isMap1()) ignoredMaps.add(1);
      if(setting.isMap2()) ignoredMaps.add(2);
      if(setting.isMap3()) ignoredMaps.add(3);
      if(setting.isMap4()) ignoredMaps.add(4);
      if(setting.isMap5()) ignoredMaps.add(5);
      if(setting.isMap6()) ignoredMaps.add(6);
      if(setting.isMap7()) ignoredMaps.add(7);
      if(setting.isMap8()) ignoredMaps.add(8);
      if(setting.isMap9()) ignoredMaps.add(9);
      if(setting.isMap10()) ignoredMaps.add(10);
      if(setting.isMap11()) ignoredMaps.add(11);
      if(setting.isMap12()) ignoredMaps.add(12);
      SortedSet<UserMapStage> corruptions = findCorruptions(username, ignoredMaps, report);
      report.append(src, "共发现" + corruptions.size() + "个反攻");
      if(corruptions.size() > 0) {
        int count = clearCorruptions(username, setting.getRetry(), corruptions, report);
        report.append(src, "共清除（" + count + "/" + corruptions.size() + "）个反攻");
      }
    }
  }

  @Override
  protected String printConfig(String username, CorruptionSetting setting) {
    return "";
  }

  private SortedSet<UserMapStage> findCorruptions(String username, Collection<Integer> ignoredMaps, AppendableReport report) throws ServerNotAvailableException, WrongCredentialException {
    String src = src(username);
    SortedSet<UserMapStage> ret = new TreeSet<>();
    SortedMap<Integer, UserMapStage> userMapStages = map.getUserMapStages(username);
    for(Map.Entry<Integer, UserMapStage> entry : userMapStages.entrySet()) {
      UserMapStage stage = entry.getValue();
      if(ignoredMaps.contains(stage.getMapStageId()) || !stage.isCorrupted()) continue;
      report.append(src, "在" + assetsService.stage(username, stage.getMapStageDetailId()) + "发现反攻");
      ret.add(stage);
    }
    return ret;
  }

  private int clearCorruptions(String username, int retry, Iterable<UserMapStage> corruptions, AppendableReport report) throws ServerNotAvailableException, WrongCredentialException {
    String src = src(username);
    int count = 0;
    for(UserMapStage corruption : corruptions) {
      int failure = 0;
      while(true) {
        BattleMap battle = map.editUserMapStages(username, corruption.getMapStageDetailId());
        if(battle == null) {
          report.append(src, "体力耗尽，离开地图");
          return count;
        }
        report.append(src, parseBattle(username, battle));
        if(battle.win()) {
          report.append(src, "已清除位于" + assetsService.stage(username, corruption.getMapStageId()) + "的反攻");
          count++;
          break;
        } else {
          failure++;
          if(failure > retry) {
            report.append(src, "战斗失败，达到最大重试次数（" + retry + "），跳过该关卡");
            break;
          } else {
            int remain = retry - failure;
            report.append(src, "战斗失败，剩余重试次数" + remain);
          }
        }
      }
    }
    return count;
  }

}
