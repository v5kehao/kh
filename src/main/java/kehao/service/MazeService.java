package kehao.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kehao.emulator.EmulatorMaze;
import kehao.emulator.EmulatorUser;
import kehao.emulator.game.model.basic.*;
import kehao.exception.*;
import kehao.exception.maze.InsufficientEnergyException;
import kehao.exception.maze.InvalidMazeLayerException;
import kehao.exception.user.RequireChangeNicknameException;
import kehao.model.MazeSetting;
import kehao.util.AppendableReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MazeService extends FunctionalServiceProvider<MazeSetting> {

  private EmulatorMaze maze;
  private EmulatorUser user;

  @Autowired
  public MazeService(LockingService locker, EmulatorMaze maze, AssetsService assetsService) {
    super(locker, assetsService);
    this.maze = maze;
  }

  @Override
  protected void run(String username, MazeSetting setting, AppendableReport report) throws RequireChangeNicknameException,
                                                                                               ServerNotAvailableException,
                                                                                               WrongCredentialException {
    String src = src(username);
    List<Integer> mazes = new ArrayList<>();
    if(setting.isMaze2()) mazes.add(2);
    if(setting.isMaze3()) mazes.add(3);
    if(setting.isMaze4()) mazes.add(4);
    if(setting.isMaze5()) mazes.add(5);
    if(setting.isMaze6()) mazes.add(6);
    if(setting.isMaze7()) mazes.add(7);
    if(setting.isMaze8()) mazes.add(8);
    if(setting.isDesc()) Collections.reverse(mazes);
    for(int maze : mazes) {
      if(!cycleMaze(username, maze, setting.getBudget(), setting.getEnergyBudget(), setting.getRetry(), setting.isShuffle(), setting.getMinDelay(), setting.getMaxDelay(), report)) {
        break;
      }
    }
  }

  @Override
  protected String printConfig(String username, MazeSetting setting) {
    return "";
  }

  private List<Integer> getEnemyIndices(MazeInfo mazeInfo, boolean includeMonster, boolean includeBox, boolean shuffle) {
    int[] items = mazeInfo.getItems();
    int guardIndex = -1;
    List<Integer> ret = new ArrayList<>();
    int item;
    for(int i = 0; i < items.length; i++) {
      item = items[i];
      switch(item) {
        case MazeInfo.Monster:
          if(includeMonster) {
            ret.add(i);
          }
          break;
        case MazeInfo.Box:
          if(includeBox) {
            ret.add(i);
          }
          break;
        case MazeInfo.UpStair:
          guardIndex = i;
          break;
      }
    }
    if(shuffle) {
      Collections.shuffle(ret);
    }
    if(guardIndex > 0 && !mazeInfo.isCleared()) {
      ret.add(guardIndex);
    }
    return ret;
  }



  private boolean clearMaze(String username, int id, int layer, int energyBudget, int retry, boolean shuffle, int minDelay, int maxDelay, AppendableReport report) throws RequireChangeNicknameException,
                                                                                                                                                                           BattleFailureException,
                                                                                                                                                                           ServerNotAvailableException,
                                                                                                                                                                           WrongCredentialException {
    String src = src(username);
    while(true) {
      report.append(src, "正在获取第" + layer + "层信息");
      MazeInfo current = maze.info(username, id, layer);
      List<Integer> enemies = getEnemyIndices(current, true, true, shuffle);
      report.append(src, "发现" + enemies.size() + "个敌人");
      int failure = 0;
      for(int enemy : enemies) {
        boolean restartMaze = false;
        while(true) {
          delay(minDelay, maxDelay);
          BattleNormal battle;
          try {
            battle = maze.battle(username, id, layer, enemy);
          } catch(InsufficientEnergyException iee) {
            if(energyBudget == 0) return false;
            UserInfo userInfo = user.getUserInfo(username);
            int cost = Math.min(400, Math.max(100, (userInfo.getEnergyBuyCount()) * 100));
            return false;
          } catch(InvalidMazeLayerException ime) {
            restartMaze = true;
            break;
          }

          report.append(src, parseBattle(username, battle));
          if(battle.lost()) {
            failure++;
            if(failure > retry) {
              report.append(src, "战斗失败，达到最大重试次数（" + retry + "），离开迷宫");
              throw new BattleFailureException();
            } else {
              int remain = retry - failure;
              report.append(src, "战斗失败，剩余重试次数" + remain);
            }
          } else {
            if(battle.mazeClear()) {
              report.append(src, "迷宫" + id + "已通关");
              return true;
            }
            break;
          }
        }
        if(restartMaze) {
          layer = 0;
          break;
        }
      }
      layer++;
      int max = current.getTotalLayer();
      if(layer > max) {
        layer = 1;
      }
    }
  }

  private boolean cycleMaze(String username, int id, int budget, int energyBudget, int retry, boolean shuffle, int minDelay, int maxDelay, AppendableReport report) throws RequireChangeNicknameException,
                                                                                                                                                                             ServerNotAvailableException,
                                                                                                                                                                             WrongCredentialException {
    String src = username + "-maze";
    while(true) {
      report.append(src, "准备进入迷宫" + id);
      MazeStatus mazeStatus = maze.show(username, id);
      report.append(src, "已经进入迷宫" + id);
      if(mazeStatus.isMazeClear()) {
        report.append(src, "迷宫" + id + "已通关");
        if(mazeStatus.allowFreeReset()) {
          report.append(src, "迷宫" + id + "可免费重置，正在重置迷宫");
          if(!maze.reset(username, id)) {
            report.append(src, "迷宫" + id + "重置失败");
            return true;
          }
          report.append(src, "迷宫" + id + "成功被重置");
        } else {
          int resetCost = mazeStatus.getResetCash();
          report.append(src, "迷宫" + id + "重置需要" + resetCost + "晶钻");
          if(resetCost <= budget) {
            report.append(src, "重置消费在预算范围（" + budget + "）内，正在重置迷宫" + id);
            if(!maze.reset(username, id)) {
              report.append(src, "迷宫" + id + "重置失败");
              return true;
            }
            report.append(src, "迷宫" + id + "成功被重置");
          } else {
            report.append(src, "重置消费高于预算（" + budget + "），放弃重置迷宫" + id);
            return true;
          }
        }
      } else {
        try {
          if(!clearMaze(username, id, mazeStatus.getLayer(), energyBudget, retry, shuffle, minDelay, maxDelay, report)) {
            report.append(src, "体力耗尽，" + "离开迷宫" + id);
            return false;
          }
        } catch(BattleFailureException e) {
          if(mazeStatus.allowFreeReset()) {
            report.append(src, "迷宫" + id + "可免费重置，正在重置迷宫");
            if(!maze.reset(username, id)) {
              report.append(src, "迷宫" + id + "重置失败");
              return true;
            }
            report.append(src, "迷宫" + id + "成功被重置");
          } else {
            report.append(src, "迷宫" + id + "不可被免费重置，放弃迷宫");
            return true;
          }
        }
      }
    }
  }
}
