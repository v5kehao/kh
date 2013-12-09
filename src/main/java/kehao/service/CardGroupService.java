package kehao.service;

import java.util.Collection;
import java.util.List;

import kehao.emulator.EmulatorCard;
import kehao.emulator.game.model.basic.CardGroup;
import kehao.emulator.game.model.basic.Group;
import kehao.exception.ServerNotAvailableException;
import kehao.exception.WrongCredentialException;
import kehao.exception.card.UnusableCardGroupException;
import kehao.util.CardGroupUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardGroupService {

  @Autowired
  public EmulatorCard card;

  public Group setDefaultCardGroup(String username, int cardGroupIndex) throws UnusableCardGroupException, ServerNotAvailableException, WrongCredentialException {
    CardGroup cardGroup = card.getCardGroup(username);
    List<Group> groups = cardGroup.getGroups();
    if(groups.size() <= cardGroupIndex) {
      throw new UnusableCardGroupException();
    }
    Group group = groups.get(cardGroupIndex);
    long defaultCardGroup = card.getDefaultCardGroup(username);
    if(defaultCardGroup != group.getGroupId()) {
      card.setDefaultCardGroup(username, group.getGroupId());
    }
    return group;
  }

  public void setCardGroup(String username, int cardGroupIndex, Collection<Long> userCardIds, Collection<Long> userRuneIds) throws UnusableCardGroupException, ServerNotAvailableException, WrongCredentialException {
    Group group = setDefaultCardGroup(username, cardGroupIndex);
    if(CardGroupUtils.hasExactly(group, userCardIds, userRuneIds)) {
      return;
    }
    card.setCardGroup(username, group.getGroupId(), userCardIds, userRuneIds);
  }



}
