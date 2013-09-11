package kehao.job;

import kehao.model.MazeSetting;
import kehao.model.UserSetting;
import kehao.service.MazeService;
import org.quartz.SchedulerContext;

public class MazeJob extends ScheduledJob<MazeService, MazeSetting> {
  public static String KEY = "MAZE";

  @Override
  protected MazeService getService(SchedulerContext context) {
    return (MazeService) context.get(KEY);
  }

  @Override
  protected MazeSetting getSetting(UserSetting userSetting) {
    return userSetting.getMazeSetting();
  }
}
