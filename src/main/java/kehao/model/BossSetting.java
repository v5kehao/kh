package kehao.model;

public class BossSetting implements UserFunctionalSetting {
  private int boss1;
  private String boss1DynamicCardGroup;
  private int boss2;
  private String boss2DynamicCardGroup;
  private int boss3;
  private String boss3DynamicCardGroup;
  private int boss4;
  private String boss4DynamicCardGroup;
  private int boss5;
  private String boss5DynamicCardGroup;
  private int boss6;
  private String boss6DynamicCardGroup;
  private int exitCardGroup;
  private String dynamicExitCardGroup;
  private boolean showBattle;
  private boolean showRank;
  private boolean time13;
  private boolean time21;
  private boolean auto;

  public static BossSetting getDefault() {
    BossSetting ret = new BossSetting();
    ret.setBoss1(0);
    ret.setBoss1DynamicCardGroup("");
    ret.setBoss2(0);
    ret.setBoss2DynamicCardGroup("");
    ret.setBoss3(0);
    ret.setBoss3DynamicCardGroup("");
    ret.setBoss4(0);
    ret.setBoss4DynamicCardGroup("");
    ret.setBoss5(0);
    ret.setBoss5DynamicCardGroup("");
    ret.setBoss6(0);
    ret.setBoss6DynamicCardGroup("");
    ret.setExitCardGroup(0);
    ret.setDynamicExitCardGroup("");
    ret.setShowBattle(false);
    ret.setShowRank(false);
    ret.setTime13(true);
    ret.setTime21(true);
    ret.setAuto(false);
    return ret;
  }

  public int getBoss1() {
    return boss1;
  }

  public void setBoss1(int boss1) {
    this.boss1 = boss1;
  }

  public String getBoss1DynamicCardGroup() {
    return boss1DynamicCardGroup;
  }

  public void setBoss1DynamicCardGroup(String boss1DynamicCardGroup) {
    this.boss1DynamicCardGroup = boss1DynamicCardGroup;
  }

  public int getBoss2() {
    return boss2;
  }

  public void setBoss2(int boss2) {
    this.boss2 = boss2;
  }

  public String getBoss2DynamicCardGroup() {
    return boss2DynamicCardGroup;
  }

  public void setBoss2DynamicCardGroup(String boss2DynamicCardGroup) {
    this.boss2DynamicCardGroup = boss2DynamicCardGroup;
  }

  public int getBoss3() {
    return boss3;
  }

  public void setBoss3(int boss3) {
    this.boss3 = boss3;
  }

  public String getBoss3DynamicCardGroup() {
    return boss3DynamicCardGroup;
  }

  public void setBoss3DynamicCardGroup(String boss3DynamicCardGroup) {
    this.boss3DynamicCardGroup = boss3DynamicCardGroup;
  }

  public int getBoss4() {
    return boss4;
  }

  public void setBoss4(int boss4) {
    this.boss4 = boss4;
  }

  public String getBoss4DynamicCardGroup() {
    return boss4DynamicCardGroup;
  }

  public void setBoss4DynamicCardGroup(String boss4DynamicCardGroup) {
    this.boss4DynamicCardGroup = boss4DynamicCardGroup;
  }

  public int getBoss5() {
    return boss5;
  }

  public void setBoss5(int boss5) {
    this.boss5 = boss5;
  }

  public String getBoss5DynamicCardGroup() {
    return boss5DynamicCardGroup;
  }

  public void setBoss5DynamicCardGroup(String boss5DynamicCardGroup) {
    this.boss5DynamicCardGroup = boss5DynamicCardGroup;
  }

  public int getBoss6() {
    return boss6;
  }

  public void setBoss6(int boss6) {
    this.boss6 = boss6;
  }

  public String getBoss6DynamicCardGroup() {
    return boss6DynamicCardGroup;
  }

  public void setBoss6DynamicCardGroup(String boss6DynamicCardGroup) {
    this.boss6DynamicCardGroup = boss6DynamicCardGroup;
  }

  public int getExitCardGroup() {
    return exitCardGroup;
  }

  public void setExitCardGroup(int exitCardGroup) {
    this.exitCardGroup = exitCardGroup;
  }

  public String getDynamicExitCardGroup() {
    return dynamicExitCardGroup;
  }

  public void setDynamicExitCardGroup(String dynamicExitCardGroup) {
    this.dynamicExitCardGroup = dynamicExitCardGroup;
  }

  public boolean isTime13() {
    return time13;
  }

  public void setTime13(boolean time13) {
    this.time13 = time13;
  }

  public boolean isTime21() {
    return time21;
  }

  public void setTime21(boolean time21) {
    this.time21 = time21;
  }

  public boolean isShowBattle() {
    return showBattle;
  }

  public void setShowBattle(boolean showBattle) {
    this.showBattle = showBattle;
  }

  public boolean isShowRank() {
    return showRank;
  }

  public void setShowRank(boolean showRank) {
    this.showRank = showRank;
  }

  public boolean isAuto() {
    return auto;
  }

  public void setAuto(boolean auto) {
    this.auto = auto;
  }
}
