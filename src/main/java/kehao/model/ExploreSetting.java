package kehao.model;

public class ExploreSetting implements UserFunctionalSetting {
  private boolean maze2;
  private boolean maze3;
  private boolean maze4;
  private boolean maze5;
  private boolean maze6;
  private boolean maze7;
  private boolean maze8;
  private int energy;
  private boolean noChip;
  private boolean noThief;
  private int hour;
  private int minute;
  private boolean auto;

  public static ExploreSetting getDefault() {
    ExploreSetting ret = new ExploreSetting();
    ret.setMaze2(false);
    ret.setMaze3(false);
    ret.setMaze4(false);
    ret.setMaze5(false);
    ret.setMaze6(true);
    ret.setMaze7(true);
    ret.setMaze8(true);
    ret.setEnergy(10);
    ret.setNoChip(true);
    ret.setNoThief(true);
    ret.setHour(3);
    ret.setMinute(30);
    ret.setAuto(false);
    return ret;
  }

  public boolean isMaze2() {
    return maze2;
  }

  public void setMaze2(boolean maze2) {
    this.maze2 = maze2;
  }

  public boolean isMaze3() {
    return maze3;
  }

  public void setMaze3(boolean maze3) {
    this.maze3 = maze3;
  }

  public boolean isMaze4() {
    return maze4;
  }

  public void setMaze4(boolean maze4) {
    this.maze4 = maze4;
  }

  public boolean isMaze5() {
    return maze5;
  }

  public void setMaze5(boolean maze5) {
    this.maze5 = maze5;
  }

  public boolean isMaze6() {
    return maze6;
  }

  public void setMaze6(boolean maze6) {
    this.maze6 = maze6;
  }

  public boolean isMaze7() {
    return maze7;
  }

  public void setMaze7(boolean maze7) {
    this.maze7 = maze7;
  }

  public boolean isMaze8() {
    return maze8;
  }

  public void setMaze8(boolean maze8) {
    this.maze8 = maze8;
  }

  public int getEnergy() {
    return energy;
  }

  public void setEnergy(int energy) {
    this.energy = energy;
  }

  public boolean isNoChip() {
    return noChip;
  }

  public void setNoChip(boolean noChip) {
    this.noChip = noChip;
  }

  public boolean isNoThief() {
    return noThief;
  }

  public void setNoThief(boolean noThief) {
    this.noThief = noThief;
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
