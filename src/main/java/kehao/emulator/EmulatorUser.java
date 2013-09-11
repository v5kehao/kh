package kehao.emulator;

import java.util.LinkedHashMap;
import java.util.Map;

import kehao.emulator.game.model.basic.UserInfo;
import kehao.emulator.game.model.response.*;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import kehao.exception.user.NicknameExistsException;
import kehao.exception.user.RequireChangeNicknameException;
import kehao.exception.user.TooManyCharactersInNicknameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorUser {

  @Autowired
  private UnknownErrorHandler unknownErrorHandler;
  @Autowired
  private EmulatorCore core;

  public boolean editNickName(String username, int sex, String inviteCode, String nickname) throws NicknameExistsException,
                                                                                                     TooManyCharactersInNicknameException,
                                                                                                     ServerNotAvailableException,
                                                                                                     WrongCredentialException {
    LoginToken token = core.getLoginToken(username);
    Map<String, String> paramMap = new LinkedHashMap<String, String>();
    paramMap.put("Sex", Integer.toString(sex));
    paramMap.put("InviteCode", inviteCode);
    paramMap.put("NickName", nickname);
    UserEditNickNameResponse response = core.gameDoAction(username, "user.php", "EditNickName", paramMap, UserEditNickNameResponse.class);
    if(response.badRequest()) {
      if(response.duplicateNickName()) {
        throw new NicknameExistsException();
      } else if(response.tooLong()) {
        throw new TooManyCharactersInNicknameException();
      } else {
        unknownErrorHandler.print(username, response.getMessage());
      }
    }
    return true;
  }

  public boolean editFresh(String username, int type, int stage) throws ServerNotAvailableException,
                                                                          WrongCredentialException {
    Map<String, String> paramMap = new LinkedHashMap<>();
    paramMap.put("FreshStep", type + "_" + stage);
    UserEditFreshResponse response = core.gameDoAction(username, "user.php", "EditFresh", paramMap, UserEditFreshResponse.class);
    if(response.badRequest()) {
      if(response.alreadyFinished()) {
        return false;
      } else {
        unknownErrorHandler.print(username, response.getMessage());
      }
    }
    return true;
  }

  public boolean getUserSalary(String username) throws ServerNotAvailableException,
                                                         WrongCredentialException {
    UserGetUserSalaryResponse response = core.gameDoAction(username, "user.php", "GetUserSalary", null, UserGetUserSalaryResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return true;
  }

  public boolean awardSalary(String username) throws ServerNotAvailableException,
                                                       WrongCredentialException {
    UserAwardSalaryResponse response = core.gameDoAction(username, "user.php", "AwardSalary", null, UserAwardSalaryResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return true;
  }

  public UserInfo getUserInfo(String username) throws RequireChangeNicknameException,
                                                        ServerNotAvailableException,
                                                        WrongCredentialException {
    UserGetUserInfoResponse response = core.gameDoAction(username, "user.php", "GetUserinfo", null, UserGetUserInfoResponse.class);
    if(response.badRequest()) {
      if(response.isRequireNickName()) {
        throw new RequireChangeNicknameException();
      }
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

}
