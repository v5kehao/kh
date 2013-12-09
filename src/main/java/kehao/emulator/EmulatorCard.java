package kehao.emulator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kehao.emulator.game.model.basic.*;
import kehao.emulator.game.model.response.*;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmulatorCard {

  @Autowired
  private EmulatorCore core;
  @Autowired
  private UnknownErrorHandler unknownErrorHandler;

  public UserCards getUserCards(String username) throws ServerNotAvailableException,
                                                          WrongCredentialException {
    CardGetUserCardsResponse response = core.gameDoAction(username, "card.php", "GetUserCards", null, CardGetUserCardsResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public CardGroup getCardGroup(String username) throws ServerNotAvailableException,
                                                          WrongCredentialException {
    CardGetCardGroupResponse response = core.gameDoAction(username, "card.php", "GetCardGroup", null, CardGetCardGroupResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public long setCardGroup(String username, long groupId, Iterable<Long> userCardIds, Iterable<Long> userRuneIds) throws ServerNotAvailableException,
                                                                                                                   WrongCredentialException {
    Map<String, String> params = new LinkedHashMap<>();
    params.put("Cards", StringUtils.join(userCardIds, "_"));
    params.put("Runes", StringUtils.join(userRuneIds, "_"));
    params.put("GroupId", Long.toString(groupId));
    CardSetCardGroupResponse response = core.gameDoAction(username, "card.php", "SetCardGroup", params, CardSetCardGroupResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData().getGroupId();
  }

  public long getDefaultCardGroup(String username) throws ServerNotAvailableException,
                                                                             WrongCredentialException {
    return -1;
  }

  public long setDefaultCardGroup(String username, long cardGroupId) throws ServerNotAvailableException,
                                                                              WrongCredentialException {
    return -1;
  }

  public AllSkill getAllSkill(String username) throws ServerNotAvailableException,
                                                        WrongCredentialException {
    CardGetAllSkillResponse response = core.gameDoAction(username, "card.php", "GetAllSkill", null, CardGetAllSkillResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

  public AllCard getAllCard(String username) throws ServerNotAvailableException,
                                                      WrongCredentialException {
    CardGetAllCardResponse response = core.gameDoAction(username, "card.php", "GetAllCard", null, CardGetAllCardResponse.class);
    if(response.badRequest()) {
      unknownErrorHandler.print(username, response.getMessage());
    }
    return response.getData();
  }

}
