package kehao.emulator.game.model.basic;

public class PassportLogin {
  private int isSetNick;
  private boolean invite;
  private boolean gscode;
  private boolean minor;
  private String cdnurl;
  private String ip;
  private int ipport;

  public int getSetNick() {
    return isSetNick;
  }

  public boolean isInvite() {
    return invite;
  }

  public boolean isGscode() {
    return gscode;
  }

  public boolean isMinor() {
    return minor;
  }

  public String getCdnurl() {
    return cdnurl;
  }

  public String getIp() {
    return ip;
  }

  public int getIpport() {
    return ipport;
  }
}
