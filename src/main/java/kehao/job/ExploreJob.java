package kehao.job;

import kehao.model.ExploreSetting;
import kehao.model.UserSetting;
import kehao.service.ExploreService;
import org.quartz.SchedulerContext;

public class ExploreJob extends ScheduledJob<ExploreService, ExploreSetting> {
  public static String KEY = "EXPLORE";

  @Override
  protected ExploreService getService(SchedulerContext context) {
    return (ExploreService) context.get(KEY);
  }

  @Override
  protected ExploreSetting getSetting(UserSetting userSetting) {
    return userSetting.getExploreSetting();
  }
}
