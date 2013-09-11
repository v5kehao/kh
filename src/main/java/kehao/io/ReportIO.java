package kehao.io;

import java.io.File;

import kehao.util.DateStringUtils;
import kehao.util.TextReport;

public class ReportIO {
  private static String dirPath = System.getProperty("user.home") + File.separator + ".kh";
  private static TextReport report;

  private static String getPath() {
    return dirPath + File.separator + DateStringUtils.dateFileName() + ".txt";
  }

  public static TextReport getReport() {
    try {
      if(report == null) {
        report = new TextReport(getPath());
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return report;
  }

  public static TextReport startNewReport() {
    if(report != null) {
      report.requireDestroy();
    }
    return report = getReport();
  }


}
