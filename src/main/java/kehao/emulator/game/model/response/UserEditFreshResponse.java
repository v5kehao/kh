package kehao.emulator.game.model.response;

import java.util.SortedMap;

public class UserEditFreshResponse extends GameData<SortedMap<String, String>> {
  public static final String AlreadyFinishedMessage = "此阶段新手引导已完成"; // 此阶段新手引导已完成!

  public boolean alreadyFinished() {
    return message != null && message.contains(AlreadyFinishedMessage);
  }
}
