package kehao.io;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kehao.model.AppSetting;
import org.apache.commons.io.FileUtils;

public class SettingIO {
  private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
  private static String dirPath = System.getProperty("user.home") + File.separator + ".kh";
  private static String settingPath = dirPath + File.separator + "setting.json";

  public static AppSetting loadSetting() {
    try {
      FileUtils.forceMkdir(new File(dirPath));
      File file = new File(settingPath);
      if(file.exists()) {
        return gson.fromJson(FileUtils.readFileToString(file), AppSetting.class);
      } else {
        AppSetting ret = AppSetting.getDefault();
        FileUtils.writeStringToFile(file, gson.toJson(ret));
        return ret;
      }
    } catch(IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void saveSetting(AppSetting setting) {
    try {
      FileUtils.forceMkdir(new File(dirPath));
      File file = new File(settingPath);
      FileUtils.writeStringToFile(file, gson.toJson(setting));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

}
