package kehao.emulator.game.model.response;

import com.google.gson.Gson;

public class GameDataFactory {
  public static <T extends GameData> T getGameData(String str, Class<T> clazz) {
    try {
      return new Gson().fromJson(str, clazz);
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
