package kehao.emulator;

import java.util.LinkedHashMap;
import java.util.Map;

import kehao.emulator.game.model.basic.FriendApplys;
import kehao.emulator.game.model.basic.Friends;
import kehao.emulator.game.model.response.FriendDisposeFriendApplyResponse;
import kehao.emulator.game.model.response.FriendGetFriendApplysResponse;
import kehao.emulator.game.model.response.FriendGetFriendsResponse;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorFriend {

  @Autowired
  private EmulatorCore core;
  @Autowired
  private UnknownErrorHandler unknownErrorHandler;

  public Friends getFriends(String username) throws ServerNotAvailableException,
                                                      WrongCredentialException {
    FriendGetFriendsResponse response = core.gameDoAction(username, "friend.php", "GetFriends", null, FriendGetFriendsResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public FriendApplys getFriendApplys(String username) throws ServerNotAvailableException,
                                                                WrongCredentialException {
    FriendGetFriendApplysResponse response = core.gameDoAction(username, "friend.php", "GetFriendApplys", null, FriendGetFriendApplysResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public boolean disposeFriendApply(String username, long friendId, boolean accept) throws ServerNotAvailableException,
                                                                                             WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<String, String>();
    String type;
    if(accept) {
      type = "agree";
    } else {
      type = "reject";
    }
    params.put("type", type);
    params.put("Fid", Long.toString(friendId));
    FriendDisposeFriendApplyResponse response = core.gameDoAction(username, "friend.php", "DisposeFriendApply", params, FriendDisposeFriendApplyResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return true;
  }
}
