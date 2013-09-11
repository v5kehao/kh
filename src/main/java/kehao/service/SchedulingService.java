package kehao.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import kehao.io.ReportIO;
import kehao.job.*;
import kehao.model.*;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulingService {

  @Autowired
  private AppSetting setting;
  @Autowired
  private Scheduler scheduler;
  @Autowired
  private BossService bossService;
  @Autowired
  private CorruptionService corruptionService;
  @Autowired
  private EnergyService energyService;
  @Autowired
  private ExploreService exploreService;
  @Autowired
  private LegionService legionService;
  @Autowired
  private MazeService mazeService;
  @Autowired
  private ThiefService thiefService;
  @Autowired
  private AdminService adminService;

  @PostConstruct
  public void init() throws Exception {
    SchedulerContext context = scheduler.getContext();
    context.put(BossJob.KEY, bossService);
    context.put(CorruptionJob.KEY, corruptionService);
    context.put(EnergyJob.KEY, energyService);
    context.put(ExploreJob.KEY, exploreService);
    context.put(LegionJob.KEY, legionService);
    context.put(MazeJob.KEY, mazeService);
    context.put(ThiefJob.KEY, thiefService);
    context.put(ScheduledJob.ADMIN, adminService);
    context.put(ScheduledJob.SETTING, setting);
    context.put(ScheduledJob.REPORT, ReportIO.startNewReport());
    scheduler.scheduleJob(JobBuilder.newJob(StartNewReportJob.class).build(),
                           TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(everyDay(0, 0))).build());
  }


  private String everyFewHour(int hour, int min) {
    return "0 " + min + " */" + hour + " * * ?";
  }

  private String everyFewMinute(int min) {
    return "0 */" + min + " * * * ?";
  }

  private String everyDay(int hour, int min) {
    return "0 " + min + " " + hour + " * * ?";
  }

  private String hours(Iterable<Integer> hours) {
     return "0 0 " + StringUtils.join(hours, ",") + " * * ?";
  }

  public void scheduleBoss(String username, BossSetting bossSetting) throws SchedulerException {
    TriggerKey triggerKey = TriggerKey.triggerKey(BossJob.KEY, username);
    List<Integer> hours = new ArrayList<>();
    if(bossSetting.isTime13()) hours.add(13);
    if(bossSetting.isTime21()) hours.add(21);
    if(bossSetting.isAuto() && (bossSetting.isTime13() || bossSetting.isTime21())) {
      Trigger trigger = TriggerBuilder.newTrigger()
                          .withIdentity(triggerKey)
                          .withSchedule(CronScheduleBuilder.cronSchedule(hours(hours)))
                          .build();
      if(scheduler.checkExists(triggerKey)) {
        scheduler.rescheduleJob(triggerKey, trigger);
      } else {
        JobDetail job = JobBuilder.newJob(BossJob.class)
                          .usingJobData(ScheduledJob.USERNAME, username)
                          .build();
        scheduler.scheduleJob(job, trigger);
      }
    } else {
      scheduler.unscheduleJob(triggerKey);
    }
  }

  public void scheduleCorruption(String username, CorruptionSetting corruptionSetting) throws SchedulerException {
    TriggerKey triggerKey = TriggerKey.triggerKey(CorruptionJob.KEY, username);
    if(corruptionSetting.isAuto()) {
      Trigger trigger = TriggerBuilder.newTrigger()
                          .withIdentity(triggerKey)
                          .withSchedule(CronScheduleBuilder.cronSchedule(everyDay(corruptionSetting.getHour(), corruptionSetting.getMinute())))
                          .build();
      if(scheduler.checkExists(triggerKey)) {
        scheduler.rescheduleJob(triggerKey, trigger);
      } else {
        JobDetail job = JobBuilder.newJob(CorruptionJob.class)
                          .usingJobData(ScheduledJob.USERNAME, username)
                          .build();
        scheduler.scheduleJob(job, trigger);
      }
    } else {
      scheduler.unscheduleJob(triggerKey);
    }
  }

  public void scheduleEnergy(String username, EnergySetting energySetting) throws SchedulerException {
    TriggerKey triggerKey = TriggerKey.triggerKey(EnergyJob.KEY, username);
    if(energySetting.isAuto()) {
      Trigger trigger = TriggerBuilder.newTrigger()
                          .withIdentity(triggerKey)
                          .withSchedule(CronScheduleBuilder.cronSchedule(everyFewHour(energySetting.getHour(), energySetting.getMinute())))
                          .build();
      if(scheduler.checkExists(triggerKey)) {
        scheduler.rescheduleJob(triggerKey, trigger);
      } else {
        JobDetail job = JobBuilder.newJob(EnergyJob.class)
                          .usingJobData(ScheduledJob.USERNAME, username)
                          .build();
        scheduler.scheduleJob(job, trigger);
      }
    } else {
      scheduler.unscheduleJob(triggerKey);
    }
  }

  public void scheduleExplore(String username, ExploreSetting exploreSetting) throws SchedulerException {
    TriggerKey triggerKey = TriggerKey.triggerKey(ExploreJob.KEY, username);
    if(exploreSetting.isAuto()) {
      Trigger trigger = TriggerBuilder.newTrigger()
                          .withIdentity(triggerKey)
                          .withSchedule(CronScheduleBuilder.cronSchedule(everyFewHour(exploreSetting.getHour(), exploreSetting.getMinute())))
                          .build();
      if(scheduler.checkExists(triggerKey)) {
        scheduler.rescheduleJob(triggerKey, trigger);
      } else {
        JobDetail job = JobBuilder.newJob(ExploreJob.class)
                          .usingJobData(ScheduledJob.USERNAME, username)
                          .build();
        scheduler.scheduleJob(job, trigger);
      }
    } else {
      scheduler.unscheduleJob(triggerKey);
    }
  }

  public void scheduleLegion(String username, LegionSetting legionSetting) throws SchedulerException {
    TriggerKey triggerKey = TriggerKey.triggerKey(LegionJob.KEY, username);
    if(legionSetting.isAuto()) {
      Trigger trigger = TriggerBuilder.newTrigger()
                          .withIdentity(triggerKey)
                          .withSchedule(CronScheduleBuilder.cronSchedule(everyDay(legionSetting.getHour(), legionSetting.getMinute())))
                          .build();
      if(scheduler.checkExists(triggerKey)) {
        scheduler.rescheduleJob(triggerKey, trigger);
      } else {
        JobDetail job = JobBuilder.newJob(LegionJob.class)
                          .usingJobData(ScheduledJob.USERNAME, username)
                          .build();
        scheduler.scheduleJob(job, trigger);
      }
    } else {
      scheduler.unscheduleJob(triggerKey);
    }
  }

  public void scheduleMaze(String username, MazeSetting mazeSetting) throws SchedulerException {
    TriggerKey triggerKey = TriggerKey.triggerKey(MazeJob.KEY, username);
    if(mazeSetting.isAuto()) {
      Trigger trigger = TriggerBuilder.newTrigger()
                          .withIdentity(triggerKey)
                          .withSchedule(CronScheduleBuilder.cronSchedule(everyFewHour(mazeSetting.getHour(), mazeSetting.getMinute())))
                          .build();
      if(scheduler.checkExists(triggerKey)) {
        scheduler.rescheduleJob(triggerKey, trigger);
      } else {
        JobDetail job = JobBuilder.newJob(MazeJob.class)
                          .usingJobData(ScheduledJob.USERNAME, username)
                          .build();
        scheduler.scheduleJob(job, trigger);
      }
    } else {
      scheduler.unscheduleJob(triggerKey);
    }
  }

  public void scheduleThief(String username, ThiefSetting thiefSetting) throws SchedulerException {
    TriggerKey triggerKey = TriggerKey.triggerKey(ThiefJob.KEY, username);
    if(thiefSetting.isAuto()) {
      Trigger trigger = TriggerBuilder.newTrigger()
                          .withIdentity(triggerKey)
                          .withSchedule(CronScheduleBuilder.cronSchedule(everyFewMinute(thiefSetting.getMinute())))
                          .build();
      if(scheduler.checkExists(triggerKey)) {
        scheduler.rescheduleJob(triggerKey, trigger);
      } else {
        JobDetail job = JobBuilder.newJob(ThiefJob.class)
                          .usingJobData(ScheduledJob.USERNAME, username)
                          .build();
        scheduler.scheduleJob(job, trigger);
      }
    } else {
      scheduler.unscheduleJob(triggerKey);
    }
  }

  public void scheduleUser(UserSetting userSetting) throws SchedulerException {
    String username = userSetting.getUsername();
    scheduleBoss(username, userSetting.getBossSetting());
    scheduleCorruption(username, userSetting.getCorruptionSetting());
    scheduleEnergy(username, userSetting.getEnergySetting());
    scheduleExplore(username, userSetting.getExploreSetting());
    scheduleLegion(username, userSetting.getLegionSetting());
    scheduleMaze(username, userSetting.getMazeSetting());
    scheduleThief(username, userSetting.getThiefSetting());
  }

  public void scheduleUser(String username) throws SchedulerException  {
    scheduleUser(adminService.user(username));
  }

  public void scheduleAllUsers() throws SchedulerException {
    for(UserSetting user : adminService.users()) {
      scheduleUser(user);
    }
  }

}
