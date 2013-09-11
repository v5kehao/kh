package kehao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kehao.emulator.game.model.basic.BattleMap;
import kehao.emulator.game.model.basic.BattleNormal;
import kehao.emulator.game.model.basic.BattleNormalExtData;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.UserNotAvailableException;
import kehao.exception.WrongCredentialException;
import kehao.model.UserFunctionalSetting;
import kehao.util.AppendableReport;
import org.apache.commons.lang3.StringUtils;

public abstract class FunctionalServiceProvider<S extends UserFunctionalSetting> {

  private final LockingService locker;
  protected AssetsService assetsService;

  private Random random = new Random();

  protected FunctionalServiceProvider(LockingService locker,  AssetsService assetsService) {
    this.locker = locker;
    this.assetsService = assetsService;
  }

  protected abstract void run(String username, S setting, AppendableReport report) throws UserNotAvailableException,
                                                                                            ServerNotAvailableException,
                                                                                            WrongCredentialException;

  protected abstract String printConfig(String username, S setting);

  public void start(String username, S setting, AppendableReport report) {
    String src = src(username);
    report.append(src, printConfig(username, setting));
    locker.lock(username);
    try {
      run(username, setting, report);
      report.finish(src);
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      locker.unlock(username);
    }
  }

  protected void delay(int min, int max) {
    if(min > 0) {
      int delay = min;
      if(max > min) {
        int diff = max - min;
        min += random.nextInt(diff);
      }
      try {
        Thread.sleep(delay);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  protected String parseBattle(String username, BattleNormal battle) {
    List<String> rewards = new ArrayList<>();
    BattleNormalExtData ext = battle.getExtData();
    BattleNormalExtData.Award award = ext.getAward();
    if(award.getCardId() > 0) rewards.add(assetsService.getCardDesc(username, award.getCardId()));
    if(award.getChip() > 0) rewards.add(assetsService.chip(username, award.getChip()));
    if(award.getHonor() > 0) rewards.add(award.getHonor() + "功勋");
    if(award.getCoins() > 0) rewards.add(award.getCoins() + "金币");
    if(award.getExp() > 0) rewards.add(award.getExp() + "经验");
    String rewardsString = StringUtils.join(rewards, "，");
    StringBuilder sb = new StringBuilder();
    sb.append("与").append(battle.getDefendPlayer().getNickName()).append("战斗");
    if(battle.win()) sb.append("取胜");
    else sb.append("被击败");
    sb.append("获得").append(rewardsString);

    BattleNormalExtData.Clear clear = ext.getClear();
    if(clear != null && clear.clear()) {
      sb.append("（通关奖励").append(clear.getCoins()).append("金币以及").append(assetsService.getCardDesc(username, clear.getCardId())).append("）");
    }
    return sb.toString();
  }

  protected String parseBattle(String username, BattleMap battle) {
    StringBuilder sb = new StringBuilder();
    sb.append("与").append(battle.getDefendPlayer().getNickName()).append("战斗");
    if(battle.win()) sb.append("获得胜利");
    else sb.append("被击败");
    return sb.append("获得").toString();
  }

  protected String src(String username) {
    return username + '-' + getClass().getSimpleName();
  }
}
