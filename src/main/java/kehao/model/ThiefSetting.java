package kehao.model;

public class ThiefSetting implements UserFunctionalSetting {
  private int cardGroup;
  private String dynamicCardGroup;
  private int exitCardGroup;
  private String dynamicExitCardGroup;
  private boolean favourSelf;
  private boolean favourElite;
  private boolean favourLowHp;
  private int minThreshold;
  private int hpThreshold;
  private int minute;
  private boolean auto;

  public static ThiefSetting getDefault() {
    ThiefSetting ret = new ThiefSetting();
    ret.setCardGroup(0);
    ret.setDynamicCardGroup("");
    ret.setExitCardGroup(0);
    ret.setDynamicExitCardGroup("");
    ret.setFavourSelf(true);
    ret.setFavourElite(true);
    ret.setFavourLowHp(true);
    ret.setMinThreshold(45);
    ret.setHpThreshold(20000);
    ret.setMinute(5);
    ret.setAuto(false);
    return ret;
  }

  public int getCardGroup() {
    return cardGroup;
  }

  public void setCardGroup(int cardGroup) {
    this.cardGroup = cardGroup;
  }

  public String getDynamicCardGroup() {
    return dynamicCardGroup;
  }

  public void setDynamicCardGroup(String dynamicCardGroup) {
    this.dynamicCardGroup = dynamicCardGroup;
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

  public boolean isFavourSelf() {
    return favourSelf;
  }

  public void setFavourSelf(boolean favourSelf) {
    this.favourSelf = favourSelf;
  }

  public boolean isFavourElite() {
    return favourElite;
  }

  public void setFavourElite(boolean favourElite) {
    this.favourElite = favourElite;
  }

  public boolean isFavourLowHp() {
    return favourLowHp;
  }

  public void setFavourLowHp(boolean favourLowHp) {
    this.favourLowHp = favourLowHp;
  }

  public int getMinThreshold() {
    return minThreshold;
  }

  public void setMinThreshold(int minThreshold) {
    this.minThreshold = minThreshold;
  }

  public int getHpThreshold() {
    return hpThreshold;
  }

  public void setHpThreshold(int hpThreshold) {
    this.hpThreshold = hpThreshold;
  }

  public int getMinute() {
    return minute;
  }

  public void setMinute(int minute) {
    this.minute = minute;
  }

  public boolean isAuto() {
    return auto;
  }

  public void setAuto(boolean auto) {
    this.auto = auto;
  }
}
