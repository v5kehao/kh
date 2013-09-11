package kehao.ui;

import java.util.List;

import kehao.emulator.EmulatorCard;
import kehao.emulator.EmulatorRune;
import kehao.emulator.EmulatorUser;
import kehao.emulator.game.model.basic.UserCard;
import kehao.emulator.game.model.basic.UserInfo;
import kehao.emulator.game.model.basic.UserRune;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

  @Autowired
  private EmulatorCard card;
  @Autowired
  private EmulatorRune rune;
  @Autowired
  private EmulatorUser user;

  @Cacheable("card")
  public List<UserCard> getUserCards(String username) {
    try {
      return card.getUserCards(username).getCards();
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Cacheable("rune")
  public List<UserRune> getUserRune(String username) {
    try {
      return rune.getUserRunes(username).getRunes();
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Cacheable("info")
  public UserInfo getUserInfo(String username) {
    try {
      return user.getUserInfo(username);
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
