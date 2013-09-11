package kehao.io;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kehao.emulator.game.model.basic.*;
import org.apache.commons.io.FileUtils;

public class AssetsIO {
  private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
  private static String dirPath = System.getProperty("user.home") + File.separator + ".kh";
  private static String cardsPath = dirPath + File.separator + "cards.json";
  private static String skillsPath = dirPath + File.separator + "skills.json";
  private static String runesPath = dirPath + File.separator + "runes.json";
  private static String mapsPath = dirPath + File.separator + "maps.json";

  private static Map<Integer, CardDef> cardMap;
  private static Map<Integer, SkillDef> skillMap;
  private static Map<Integer, RuneDef> runeMap;
  private static Map<Integer, MapDef> mapMap;
  private static Map<Integer, MapStageDef> stageMap;

  private static <T> T loadAssets(String path, Class<T> clazz) {
    try {
      FileUtils.forceMkdir(new File(dirPath));
      File file = new File(path);
      if(file.exists()) {
        return gson.fromJson(FileUtils.readFileToString(file), clazz);
      } else {
        return null;
      }
    } catch(IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private static void saveAssets(Object assets, String path) {
    try {
      FileUtils.forceMkdir(new File(dirPath));
      File file = new File(path);
      FileUtils.writeStringToFile(file, gson.toJson(assets));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  private static Map<Integer, CardDef> prepareCardMap(AllCard cards) {
    cardMap = new TreeMap<Integer, CardDef>();
    for(CardDef card : cards.getCards()) {
      cardMap.put(card.getCardId(), card);
    }
    return cardMap;
  }

  private static Map<Integer, SkillDef> prepareSkillMap(AllSkill skills) {
    skillMap = new TreeMap<Integer, SkillDef>();
    for(SkillDef skill : skills.getSkills()) {
      skillMap.put(skill.getSkillId(), skill);
    }
    return skillMap;
  }

  public static Map<Integer, RuneDef> prepareRuneMap(Runes runes) {
    runeMap = new TreeMap<Integer, RuneDef>();
    for(RuneDef rune : runes.getRunes()) {
      runeMap.put(rune.getRuneId(), rune);
    }
    return runeMap;
  }

  public static Map<Integer, MapStageDef> prepareStageMap(AllMap maps) {
    stageMap = new TreeMap<Integer, MapStageDef>();
    mapMap = new TreeMap<Integer, MapDef>();
    for(MapDef map : maps) {
      mapMap.put(map.getMapStageId(), map);
      for(MapStageDef stage : map.getMapStageDetails()) {
        stageMap.put(stage.getMapStageDetailId(), stage);
      }
    }
    return stageMap;
  }

  public static AllCard loadCards() {
    return loadAssets(cardsPath, AllCard.class);
  }

  public static AllSkill loadSkills() {
    return loadAssets(skillsPath, AllSkill.class);
  }

  public static Runes loadRunes() {
    return loadAssets(runesPath, Runes.class);
  }

  public static AllMap loadMaps() {
    return loadAssets(mapsPath, AllMap.class);
  }

  public static Map<Integer, CardDef> saveCards(AllCard cards) {
    saveAssets(cards, cardsPath);
    return prepareCardMap(cards);
  }

  public static Map<Integer, SkillDef> saveSkills(AllSkill skills) {
    saveAssets(skills, skillsPath);
    return prepareSkillMap(skills);
  }

  public static Map<Integer, RuneDef> saveRunes(Runes runes) {
    saveAssets(runes, runesPath);
    return prepareRuneMap(runes);
  }

  public static Map<Integer, MapDef> saveMaps(AllMap maps) {
    saveAssets(maps, mapsPath);
    prepareStageMap(maps);
    return mapMap;
  }

  public static Map<Integer, MapStageDef> saveStages(AllMap maps) {
    saveAssets(maps, mapsPath);
    return prepareStageMap(maps);
  }


  public static CardDef getCard(int id) {
    if(cardMap != null) {
      return cardMap.get(id);
    } else {
      AllCard cards = loadCards();
      if(cards == null) {
        return null;
      } else {
        return prepareCardMap(cards).get(id);
      }
    }
  }

  public static SkillDef getSkill(int id) {
    if(skillMap != null) {
      return skillMap.get(id);
    } else {
      AllSkill skills = loadSkills();
      if(skills == null) {
        return null;
      } else {
        return prepareSkillMap(skills).get(id);
      }
    }
  }

  public static RuneDef getRune(int id) {
    if(runeMap != null) {
      return runeMap.get(id);
    } else {
      Runes runes = loadRunes();
      if(runes == null) {
        return null;
      } else {
        return prepareRuneMap(runes).get(id);
      }
    }
  }

  public static MapDef getMap(int id) {
    if(mapMap != null) {
      return mapMap.get(id);
    } else {
      AllMap maps = loadMaps();
      if(maps == null) {
        return null;
      } else {
        prepareStageMap(maps);
        return mapMap.get(id);
      }
    }
  }

  public static MapStageDef getStage(int id) {
    if(stageMap != null) {
      return stageMap.get(id);
    } else {
      AllMap maps = loadMaps();
      if(maps == null) {
        return null;
      } else {
        return prepareStageMap(maps).get(id);
      }
    }
  }

}
