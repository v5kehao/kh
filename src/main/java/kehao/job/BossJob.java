package kehao.job;

import kehao.model.BossSetting;
import kehao.model.UserSetting;
import kehao.service.BossService;
import org.quartz.SchedulerContext;

public class BossJob extends ScheduledJob<BossService, BossSetting> {
  public static String KEY = "BOSS";

  @Override
  protected BossService getService(SchedulerContext context) {
    return (BossService) context.get(KEY);
  }

  @Override
  protected BossSetting getSetting(UserSetting userSetting) {
    return userSetting.getBossSetting();
  }
}
