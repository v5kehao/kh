package kehao;

public class KhConstants {
  public static enum GameProvider {
    NORMAL("NORMAL", "IP", "CARD-ANDROID-CHS");

    private final String description;
    private final String address;
    private final String client;

    private GameProvider(String description, String address, String client) {
      this.description = description;
      this.address = address;
      this.client = client;
    }

    public String getDescription() {
      return description;
    }

    public String getAddress() {
      return address;
    }

    public String getClient() {
      return client;
    }

    @Override
    public String toString() {
      return description;
    }
  }
}
