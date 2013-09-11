package kehao.model;

import kehao.emulator.BasicAccount;

public class UserSetting extends BasicAccount {

  private long createTimestamp;
  private String nickname;
  private long uid;
  private String server;
  private BossSetting bossSetting;
  private CorruptionSetting corruptionSetting;
  private EnergySetting energySetting;
  private ExploreSetting exploreSetting;
  private LegionSetting legionSetting;
  private MazeSetting mazeSetting;
  private ThiefSetting thiefSetting;

  public UserSetting(String username, String password, String mac, String server, String nickname, long uid) {
    super(username, password, mac);
    this.createTimestamp = System.currentTimeMillis();
    this.server = server;
    this.nickname = nickname;
    this.uid = uid;
  }

  public static UserSetting getDefault(String username, String password, String nickname, long uid, String server, String mac) {
    UserSetting ret = new UserSetting(username, password, mac, server, nickname, uid);
    ret.setBossSetting(BossSetting.getDefault());
    ret.setCorruptionSetting(CorruptionSetting.getDefault());
    ret.setEnergySetting(EnergySetting.getDefault());
    ret.setExploreSetting(ExploreSetting.getDefault());
    ret.setLegionSetting(LegionSetting.getDefault());
    ret.setMazeSetting(MazeSetting.getDefault());
    ret.setThiefSetting(ThiefSetting.getDefault());
    return ret;
  }

  public long getCreateTimestamp() {
    return createTimestamp;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public String getServer() {
    return server;
  }

  public void setServer(String server) {
    this.server = server;
  }

  public BossSetting getBossSetting() {
    return bossSetting;
  }

  public void setBossSetting(BossSetting bossSetting) {
    this.bossSetting = bossSetting;
  }

  public CorruptionSetting getCorruptionSetting() {
    return corruptionSetting;
  }

  public void setCorruptionSetting(CorruptionSetting corruptionSetting) {
    this.corruptionSetting = corruptionSetting;
  }

  public EnergySetting getEnergySetting() {
    return energySetting;
  }

  public void setEnergySetting(EnergySetting energySetting) {
    this.energySetting = energySetting;
  }

  public ExploreSetting getExploreSetting() {
    return exploreSetting;
  }

  public void setExploreSetting(ExploreSetting exploreSetting) {
    this.exploreSetting = exploreSetting;
  }

  public LegionSetting getLegionSetting() {
    return legionSetting;
  }

  public void setLegionSetting(LegionSetting legionSetting) {
    this.legionSetting = legionSetting;
  }

  public MazeSetting getMazeSetting() {
    return mazeSetting;
  }

  public void setMazeSetting(MazeSetting mazeSetting) {
    this.mazeSetting = mazeSetting;
  }

  public ThiefSetting getThiefSetting() {
    return thiefSetting;
  }

  public void setThiefSetting(ThiefSetting thiefSetting) {
    this.thiefSetting = thiefSetting;
  }
}
