package kehao.util;

import java.util.Set;
import java.util.TreeSet;

public class UserConfigUtils {
  private static Set<Long> extractIds(String idString) {
    String[] ids = idString.split(";");
    Set<Long> ret = new TreeSet<Long>();
    for(String card : ids) {
      String[] idDesc = card.split("\\(");
      if(idDesc.length < 1) {
        continue;
      }
      String id = idDesc[0];
      try {
        ret.add(Long.parseLong(id));
      } catch(NumberFormatException e) {
        e.printStackTrace();
      }
    }
    return ret;
  }

  public static Set<Long> getUserCards(String cardGroupConfig) {
    if(cardGroupConfig == null || (cardGroupConfig = cardGroupConfig.trim()).isEmpty()) {
      return null;
    }
    String[] config = cardGroupConfig.split("//");
    if(config.length < 1) {
      return null;
    }
    String cardConfig = config[0];
    if((cardConfig = cardConfig.trim()).isEmpty()) {
      return null;
    }
    Set<Long> ret = extractIds(cardConfig);
    if(ret.size() == 0) {
      return null;
    }
    return ret;
  }

  public static Set<Long> getUserRunes(String cardGroupConfig) {
    if(cardGroupConfig == null || (cardGroupConfig = cardGroupConfig.trim()).isEmpty()) {
      return null;
    }
    String[] config = cardGroupConfig.split("//");
    if(config.length < 2) {
      return new TreeSet<Long>();
    }
    String runeConfig = config[1];
    if((runeConfig = runeConfig.trim()).isEmpty()) {
      return new TreeSet<Long>();
    }
    return extractIds(runeConfig);
  }
}
