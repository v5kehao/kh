package kehao.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kehao.StartApp;
import kehao.cmd.CmdArgProcessor;
import kehao.emulator._EmulatorPackageMarker;
import kehao.io.SettingIO;
import kehao.model.AppSetting;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.*;

@Configuration
@Import({EmulatorConfig.class, ServiceConfig.class})
@ComponentScan(basePackageClasses = CmdArgProcessor.class)
public class AppConfig {

  @Bean
  public AppSetting getSetting() {
    return SettingIO.loadSetting();
  }

  @Bean(destroyMethod = "shutdown")
  public ExecutorService getTaskExecutors() {
    return Executors.newScheduledThreadPool(20);
  }

  @Bean(destroyMethod = "shutdown")
  public Scheduler getScheduler() throws SchedulerException {
    Scheduler ret = new StdSchedulerFactory().getScheduler();
    ret.start();
    return ret;
  }
}
