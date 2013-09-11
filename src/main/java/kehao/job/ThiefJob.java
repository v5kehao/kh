package kehao.job;

import kehao.model.ThiefSetting;
import kehao.model.UserSetting;
import kehao.service.ThiefService;
import org.quartz.SchedulerContext;

public class ThiefJob extends ScheduledJob<ThiefService, ThiefSetting> {
  public static String KEY = "THIEF";

  @Override
  protected ThiefService getService(SchedulerContext context) {
    return (ThiefService) context.get(KEY);
  }

  @Override
  protected ThiefSetting getSetting(UserSetting userSetting) {
    return userSetting.getThiefSetting();
  }
}
