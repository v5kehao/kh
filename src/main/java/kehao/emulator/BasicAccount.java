package kehao.emulator;

public class BasicAccount {
  private String username;
  private String password;
  private String mac;

  public BasicAccount(String username, String password, String mac) {
    this.username = username;
    this.password = password;
    this.mac = mac;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac;
  }
}
