package kehao.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import kehao.emulator.*;
import kehao.emulator.game.model.basic.UserInfo;
import kehao.io.SettingIO;
import kehao.model.AppSetting;
import kehao.model.UserSetting;
import kehao.util.MacAddressHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AccountCredentialProvider {

  @Autowired
  private AppSetting setting;
  @Autowired
  private EmulatorCore core;
  @Autowired
  private EmulatorWeb web;
  @Autowired
  private EmulatorLogin login;
  @Autowired
  private EmulatorUser user;

  private Map<String, UserSetting> userSettingMap = new HashMap<String, UserSetting>();

  @PostConstruct
  public void init() {
    for(UserSetting userSetting : users()) {
      userSettingMap.put(userSetting.getUsername(), userSetting);
    }
  }

  public UserSetting addAccount(String username, String password) {
    String mac = MacAddressHelper.getMacAddress();
    try {
      core.insureConnection(username, password, mac);
      String server = core.getLoginToken(username).getServerName();
      UserInfo info = user.getUserInfo(username);
      String nickname = info.getNickName();
      UserSetting userSetting = UserSetting.getDefault(username, password, nickname, info.getUid(), server, mac);
      users().add(userSetting);
      userSettingMap.put(username, userSetting);
      save();
      return userSetting;
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void removeAccount(String username) {
    UserSetting userSetting = userSettingMap.remove(username);
    users().remove(userSetting);
    save();
  }

  public List<UserSetting> users() {
    return setting.getUserSettings();
  }

  public UserSetting user(String username) {
    return userSettingMap.get(username);
  }

  public void save() {
    SettingIO.saveSetting(setting);

  }

  @Override
  public BasicAccount getAccount(String username) {
    return user(username);
  }
}
