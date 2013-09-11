package kehao.emulator.game.model.response;

import kehao.emulator.game.model.basic.BossFight;

public class BossFightResponse extends GameData<BossFight> {
  public static final String AlreadyInQueueMessage = "您已经在等待队列中"; // 您已经在等待队列中!
  public static final String BossUnavailableMessage = "Boss还未刷新或已逃走"; // Boss还未刷新或已逃走
  public static final String BossDownMessage = "有手快的已经把Boss打掉啦"; // 有手快的已经把Boss打掉啦~
  public static final String CoolingDownMessage = "冷却时间未到"; // 冷却时间未到，请耐心等待！您也可以使用晶钻立即结束冷却时间！

  public boolean isAlreadyInQueue() {
    return message != null && message.contains(AlreadyInQueueMessage);
  }

  public boolean isBossUnavailable() {
    return message != null && message.contains(BossUnavailableMessage);
  }

  public boolean isBossDown() {
    return message != null && message.contains(BossDownMessage);
  }

  public boolean isCoolingDown() {
    return message != null && message.contains(CoolingDownMessage);
  }
}
