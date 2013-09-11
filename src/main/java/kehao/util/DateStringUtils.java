package kehao.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateStringUtils {

  private static DateFormat time = new SimpleDateFormat("HH:mm:ss");
  private static DateFormat dateFileName = new SimpleDateFormat("yyyyMMdd-HHmmssSSS");

  public static String time() {
    return time.format(Calendar.getInstance().getTime());
  }

  public static String dateFileName() {
    return dateFileName.format(Calendar.getInstance().getTime());
  }

  public static String secondsToString(int seconds) {
    int s = seconds % 60;
    int m = seconds / 60 % 60;
    int h = seconds / 3600;
    StringBuilder sb = new StringBuilder();
    if(h > 0) sb.append(h).append("小时");
    if(m > 0) sb.append(m).append("分");
    sb.append(s).append("秒");
    return sb.toString();
  }

}
