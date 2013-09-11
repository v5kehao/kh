package kehao.model;

import java.util.ArrayList;
import java.util.List;

public class AppSetting {

  private List<UserSetting> userSettings;

  public static AppSetting getDefault() {
    AppSetting ret = new AppSetting();
    ret.setUserSettings(new ArrayList<UserSetting>());
    return ret;
  }

  public List<UserSetting> getUserSettings() {
    return userSettings;
  }

  public void setUserSettings(List<UserSetting> userSettings) {
    this.userSettings = userSettings;
  }
}
