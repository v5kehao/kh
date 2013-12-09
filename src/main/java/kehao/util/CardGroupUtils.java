package kehao.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import kehao.emulator.game.model.basic.Group;
import kehao.emulator.game.model.basic.UserCard;
import kehao.emulator.game.model.basic.UserRune;

public class CardGroupUtils {
  public static boolean hasExactly(Group group, Collection<Long> userCardIds, Collection<Long> userRuneIds) {
    Set<Long> currentUserCardIds = new HashSet<>();
    for(UserCard userCard : group.getUserCardInfo()) {
      currentUserCardIds.add(userCard.getUserCardId());
    }
    if(userCardIds.size() != currentUserCardIds.size() || !currentUserCardIds.containsAll(userCardIds)) {
      return false;
    }
    Set<Long> currentUserRuneIds = new HashSet<>();
    for(UserRune userRune : group.getUserRune()) {
      currentUserRuneIds.add(userRune.getUserRuneId());
    }
    return !(userRuneIds.size() != currentUserRuneIds.size() || !currentUserRuneIds.containsAll(userRuneIds));
  }
}
