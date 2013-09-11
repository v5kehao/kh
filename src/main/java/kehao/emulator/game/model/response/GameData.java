package kehao.emulator.game.model.response;

import java.util.List;

import kehao.emulator.game.model.basic.AchievementInfo;
import kehao.emulator.game.model.basic.Version;

public class GameData<T>  {

  public static final String BadRequestString = "错误"; // 输入错误,请重新输入!
  public static final String DisconnectedString = "断开";  // 网络链接已断开 请重新登录

  protected int status;
  protected String message;
  protected T data;
  protected int type;
  protected Version version;
  protected List<AchievementInfo> AchievementInfos;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public Version getVersion() {
    return version;
  }

  public void setVersion(Version version) {
    this.version = version;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public List<AchievementInfo> getAchievementInfos() {
    return AchievementInfos;
  }

  public void setAchievementInfos(List<AchievementInfo> achievementInfos) {
    AchievementInfos = achievementInfos;
  }

  public boolean badRequest() {
    return status == 0 || (message != null && message.contains(BadRequestString));
  }

  public boolean disconnected() {
    return message != null && message.contains(DisconnectedString);
  }

}
