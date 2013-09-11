package kehao.service;

import java.util.Map;
import java.util.Set;

import kehao.emulator.*;
import kehao.emulator.game.model.basic.*;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.UserNotAvailableException;
import kehao.exception.WrongCredentialException;
import kehao.model.ExploreSetting;
import kehao.util.AppendableReport;
import kehao.util.DateStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExploreService extends FunctionalServiceProvider<ExploreSetting> {

  private EmulatorMapStage map;
  private EmulatorUser user;
  private EmulatorMaze maze;
  private EmulatorArena arena;
  private EmulatorChip chip;

  @Autowired
  public ExploreService(LockingService locker, EmulatorMapStage map, EmulatorUser user, EmulatorMaze maze, EmulatorArena arena, EmulatorChip chip, AssetsService assetsService) {
    super(locker, assetsService);
    this.user = user;
    this.map = map;
    this.maze = maze;
    this.arena = arena;
    this.chip = chip;
  }

  @Override
  protected void run(String username, ExploreSetting setting, AppendableReport report) throws UserNotAvailableException,
                                                                                                  ServerNotAvailableException,
                                                                                                  WrongCredentialException {
    String src = src(username);
    int energy = user.getUserInfo(username).getEnergy();
    int energyThreshold = setting.getEnergy();
    int max = 1;
    if(energy <= energyThreshold) {
      report.append(src, "现有体力" + energy + "低于启动条件（≤" + energyThreshold + "），放弃探索");
      return;
    }
    max += (energy - energyThreshold) / 2;

    if(setting.isMaze8() && isMazeAvailable(username, 8)
         || setting.isMaze7() && isMazeAvailable(username, 7)
         || setting.isMaze6() && isMazeAvailable(username, 6)
         || setting.isMaze5() && isMazeAvailable(username, 5)
         || setting.isMaze4() && isMazeAvailable(username, 4)
         || setting.isMaze3() && isMazeAvailable(username, 3)
         || setting.isMaze2() && isMazeAvailable(username, 2)) {
      return;
    }
    boolean stopWhenFoundThief = setting.isNoThief();
    if(stopWhenFoundThief) {
      int thiefCoolDown = arena.getThieves(username).getCountdown();
      if(thiefCoolDown > 0) {
        report.append(src, "打贼冷却时间剩余" + DateStringUtils.secondsToString(thiefCoolDown) + "放弃探索");
        return;
      }
    }
    boolean stopWhenFoundChip = setting.isNoChip();
    Set<Integer> missingChips = null;
    if(stopWhenFoundChip) {
      Map<Integer, Chip> chips = chip.getUserChip(username);
      for(Chip chip : chips.values()) {
        if(chip.isFromStage() && !chip.obtained()) {
          missingChips.add(chip.getId());
          report.append(src, "缺少碎片" + chip.getId() + "（通过探索获得）");
        }
      }
      if(chips.isEmpty()) {
        report.append(src, "未发现任何可通过探索获得的碎片，放弃探索");
        return;
      }
    }

    int stage = findStage(username);

    explore(username, stage, max, stopWhenFoundThief, missingChips);

  }

  @Override
  protected String printConfig(String username, ExploreSetting setting) {
    return "";
  }


  public boolean isMazeAvailable(String username, int id) throws ServerNotAvailableException, WrongCredentialException {
    MazeStatus status = maze.show(username, id);
    return (!status.isMazeClear() || status.allowFreeReset());
  }

  public int findStage(String username) throws ServerNotAvailableException, WrongCredentialException {
    int max = 1;
    for(UserMapStage stage : map.getUserMapStages(username).values()) {
      if(stage.getFinishedStage() == 3) {
        int id = stage.getMapStageDetailId();
        if(id > max) max = id;
      }
    }
    return max;
  }

  private void explore(String username, int id, int max, boolean stopWhenFoundThief, Set<Integer> missingChips) throws ServerNotAvailableException, WrongCredentialException {
    for(int i = 0; i < max; i++) {
      Explore result = map.explore(username, id);
      if(missingChips != null) {
        int chip = result.getChip();
        if(chip != -1 && missingChips.remove(chip) && missingChips.isEmpty()) return;
      }
      if(stopWhenFoundThief) {
        if(result.getThievesInfo() != null) return;
      }
    }
  }
}
