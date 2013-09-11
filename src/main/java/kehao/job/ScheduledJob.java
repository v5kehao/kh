package kehao.job;

import kehao.model.AppSetting;
import kehao.model.UserFunctionalSetting;
import kehao.model.UserSetting;
import kehao.service.AdminService;
import kehao.service.FunctionalServiceProvider;
import kehao.util.AppendableReport;
import org.quartz.*;

public abstract class ScheduledJob<SP extends FunctionalServiceProvider<S>, S extends UserFunctionalSetting> implements Job {


  public static String USERNAME = "USERNAME";
  public static String SETTING = "SETTING";
  public static String REPORT = "REPORT";
  public static String ADMIN = "ADMIN";

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    try {
      SchedulerContext schedulerContext = context.getScheduler().getContext();
      AppendableReport report = (AppendableReport) schedulerContext.get(REPORT);
      AppSetting appSetting = (AppSetting) schedulerContext.get(SETTING);
      SP service = getService(schedulerContext);
      JobDataMap data = context.getJobDetail().getJobDataMap();
      String username = data.getString(USERNAME);
      AdminService admin = (AdminService) schedulerContext.get(ADMIN);
      S setting = getSetting(admin.user(username));
      service.start(username, setting, report);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  protected abstract SP getService(SchedulerContext context);
  protected abstract S getSetting(UserSetting userSetting);
}
