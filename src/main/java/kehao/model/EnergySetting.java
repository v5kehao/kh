package kehao.model;

public class EnergySetting implements UserFunctionalSetting {
  private boolean favourReturn;
  private boolean favourLegion;
  private boolean favourRank;
  private int hour;
  private int minute;
  private boolean auto;

  public static EnergySetting getDefault() {
    EnergySetting ret = new EnergySetting();
    ret.setFavourReturn(true);
    ret.setFavourLegion(true);
    ret.setFavourRank(true);
    ret.setHour(3);
    ret.setMinute(40);
    ret.setAuto(false);
    return ret;
  }

  public boolean isFavourReturn() {
    return favourReturn;
  }

  public void setFavourReturn(boolean favourReturn) {
    this.favourReturn = favourReturn;
  }

  public boolean isFavourLegion() {
    return favourLegion;
  }

  public void setFavourLegion(boolean favourLegion) {
    this.favourLegion = favourLegion;
  }

  public boolean isFavourRank() {
    return favourRank;
  }

  public void setFavourRank(boolean favourRank) {
    this.favourRank = favourRank;
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
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
