package kehao.job;

import kehao.io.ReportIO;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class StartNewReportJob implements Job {

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    try {
      context.getScheduler().getContext().put(ScheduledJob.REPORT, ReportIO.startNewReport());
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
