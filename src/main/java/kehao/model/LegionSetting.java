package kehao.model;

public class LegionSetting implements UserFunctionalSetting {
  private int amount;
  private int tech;
  private int hour;
  private int minute;
  private boolean auto;

  public static LegionSetting getDefault() {
    LegionSetting ret = new LegionSetting();
    ret.setAmount(50000);
    ret.setTech(1);
    ret.setHour(23);
    ret.setMinute(50);
    ret.setAuto(false);
    return ret;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getTech() {
    return tech;
  }

  public void setTech(int tech) {
    this.tech = tech;
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
