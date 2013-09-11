package kehao.model;

public class MazeSetting implements UserFunctionalSetting {
  private int cardGroup;
  private String dynamicCardGroup;
  private int exitCardGroup;
  private String dynamicExitCardGroup;
  private boolean maze2;
  private boolean maze3;
  private boolean maze4;
  private boolean maze5;
  private boolean maze6;
  private boolean maze7;
  private boolean maze8;
  private boolean desc;
  private int budget;
  private int energyBudget;
  private int retry;
  private boolean shuffle;
  private int minDelay;
  private int maxDelay;
  private int hour;
  private int minute;
  private boolean auto;

  public static MazeSetting getDefault() {
    MazeSetting ret = new MazeSetting();
    ret.setCardGroup(0);
    ret.setDynamicCardGroup("");
    ret.setExitCardGroup(0);
    ret.setDynamicExitCardGroup("");
    ret.setMaze2(false);
    ret.setMaze3(false);
    ret.setMaze4(false);
    ret.setMaze5(false);
    ret.setMaze6(true);
    ret.setMaze7(true);
    ret.setMaze8(true);
    ret.setDesc(true);
    ret.setBudget(0);
    ret.setEnergyBudget(0);
    ret.setRetry(5);
    ret.setShuffle(true);
    ret.setMinDelay(1000);
    ret.setMaxDelay(5000);
    ret.setHour(2);
    ret.setMinute(20);
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

  public boolean isDesc() {
    return desc;
  }

  public void setDesc(boolean desc) {
    this.desc = desc;
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }

  public int getEnergyBudget() {
    return energyBudget;
  }

  public void setEnergyBudget(int energyBudget) {
    this.energyBudget = energyBudget;
  }

  public int getRetry() {
    return retry;
  }

  public void setRetry(int retry) {
    this.retry = retry;
  }

  public boolean isShuffle() {
    return shuffle;
  }

  public void setShuffle(boolean shuffle) {
    this.shuffle = shuffle;
  }

  public int getMinDelay() {
    return minDelay;
  }

  public void setMinDelay(int minDelay) {
    this.minDelay = minDelay;
  }

  public int getMaxDelay() {
    return maxDelay;
  }

  public void setMaxDelay(int maxDelay) {
    this.maxDelay = maxDelay;
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
