package kehao.job;

import kehao.model.CorruptionSetting;
import kehao.model.UserSetting;
import kehao.service.CorruptionService;
import org.quartz.SchedulerContext;

public class CorruptionJob extends ScheduledJob<CorruptionService, CorruptionSetting> {
  public static String KEY = "CORRUPTION";

  @Override
  protected CorruptionService getService(SchedulerContext context) {
    return (CorruptionService) context.get(KEY);
  }

  @Override
  protected CorruptionSetting getSetting(UserSetting userSetting) {
    return userSetting.getCorruptionSetting();
  }
}
