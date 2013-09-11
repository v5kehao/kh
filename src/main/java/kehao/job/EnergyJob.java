package kehao.job;

import kehao.model.EnergySetting;
import kehao.model.UserSetting;
import kehao.service.EnergyService;
import org.quartz.SchedulerContext;

public class EnergyJob extends ScheduledJob<EnergyService, EnergySetting> {
  public static String KEY = "ENERGY";

  @Override
  protected EnergyService getService(SchedulerContext context) {
    return (EnergyService) context.get(KEY);
  }

  @Override
  protected EnergySetting getSetting(UserSetting userSetting) {
    return userSetting.getEnergySetting();
  }
}
