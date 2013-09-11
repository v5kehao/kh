package kehao.model;

public class CorruptionSetting implements UserFunctionalSetting {
  private int cardGroup;
  private String dynamicCardGroup;
  private int exitCardGroup;
  private String dynamicExitCardGroup;
  private boolean map1;
  private boolean map2;
  private boolean map3;
  private boolean map4;
  private boolean map5;
  private boolean map6;
  private boolean map7;
  private boolean map8;
  private boolean map9;
  private boolean map10;
  private boolean map11;
  private boolean map12;
  private int energy;
  private int retry;
  private int hour;
  private int minute;
  private boolean auto;

  public static CorruptionSetting getDefault() {
    CorruptionSetting ret = new CorruptionSetting();
    ret.setCardGroup(0);
    ret.setDynamicCardGroup("");
    ret.setExitCardGroup(0);
    ret.setDynamicExitCardGroup("");
    ret.setMap1(false);
    ret.setMap2(false);
    ret.setMap3(true);
    ret.setMap4(true);
    ret.setMap5(true);
    ret.setMap6(true);
    ret.setMap7(true);
    ret.setMap8(true);
    ret.setMap9(true);
    ret.setMap10(true);
    ret.setMap11(true);
    ret.setMap12(true);
    ret.setEnergy(10);
    ret.setRetry(5);
    ret.setHour(6);
    ret.setMinute(30);
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

  public boolean isMap1() {
    return map1;
  }

  public void setMap1(boolean map1) {
    this.map1 = map1;
  }

  public boolean isMap2() {
    return map2;
  }

  public void setMap2(boolean map2) {
    this.map2 = map2;
  }

  public boolean isMap3() {
    return map3;
  }

  public void setMap3(boolean map3) {
    this.map3 = map3;
  }

  public boolean isMap4() {
    return map4;
  }

  public void setMap4(boolean map4) {
    this.map4 = map4;
  }

  public boolean isMap5() {
    return map5;
  }

  public void setMap5(boolean map5) {
    this.map5 = map5;
  }

  public boolean isMap6() {
    return map6;
  }

  public void setMap6(boolean map6) {
    this.map6 = map6;
  }

  public boolean isMap7() {
    return map7;
  }

  public void setMap7(boolean map7) {
    this.map7 = map7;
  }

  public boolean isMap8() {
    return map8;
  }

  public void setMap8(boolean map8) {
    this.map8 = map8;
  }

  public boolean isMap9() {
    return map9;
  }

  public void setMap9(boolean map9) {
    this.map9 = map9;
  }

  public boolean isMap10() {
    return map10;
  }

  public void setMap10(boolean map10) {
    this.map10 = map10;
  }

  public boolean isMap11() {
    return map11;
  }

  public void setMap11(boolean map11) {
    this.map11 = map11;
  }

  public boolean isMap12() {
    return map12;
  }

  public void setMap12(boolean map12) {
    this.map12 = map12;
  }

  public int getEnergy() {
    return energy;
  }

  public void setEnergy(int energy) {
    this.energy = energy;
  }

  public int getRetry() {
    return retry;
  }

  public void setRetry(int retry) {
    this.retry = retry;
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
