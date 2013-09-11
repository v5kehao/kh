package kehao.job;

import kehao.model.LegionSetting;
import kehao.model.UserSetting;
import kehao.service.LegionService;
import org.quartz.SchedulerContext;

public class LegionJob extends ScheduledJob<LegionService, LegionSetting> {
  public static String KEY = "LEGION";

  @Override
  protected LegionService getService(SchedulerContext context) {
    return (LegionService) context.get(KEY);
  }

  @Override
  protected LegionSetting getSetting(UserSetting userSetting) {
    return userSetting.getLegionSetting();
  }
}
