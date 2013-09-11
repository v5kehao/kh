package kehao.service;

import kehao.emulator.EmulatorCard;
import kehao.emulator.EmulatorMapStage;
import kehao.emulator.EmulatorRune;
import kehao.emulator.game.model.basic.*;
import kehao.io.AssetsIO;
import kehao.util.AssetsOracle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetsService implements AssetsOracle {

  @Autowired
  private EmulatorCard card;
  @Autowired
  private EmulatorMapStage map;
  @Autowired
  private EmulatorRune rune;

  public CardDef getCardDef(String username, int id) {
    if(id < 1) return null;
    CardDef cardDef = AssetsIO.getCard(id);
    try {
      if(cardDef == null) {
        cardDef = AssetsIO.saveCards(card.getAllCard(username)).get(id);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return cardDef;
  }

  public String getCardDesc(String username, int id) {
    CardDef cardDef = getCardDef(username, id);
    if(cardDef == null) {
      return "未知卡牌" + id;
    }
    return cardDef.getColor() + "星卡牌" + cardDef.getCardName();
  }

  public SkillDef getSkillDef(String username, int id) {
    if(id < 1) return null;
    SkillDef skillDef = AssetsIO.getSkill(id);
    try {
      if(skillDef == null) {
        skillDef = AssetsIO.saveSkills(card.getAllSkill(username)).get(id);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return skillDef;
  }

  public RuneDef getRuneDef(String username, int id) {
    RuneDef runeDef = AssetsIO.getRune(id);
    try {
      if(runeDef == null) {
        runeDef = AssetsIO.saveRunes(rune.getAllRune(username)).get(id);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return runeDef;
  }

  public String chip(String username, int id) {
    return "碎片" + id;
  }

  public String stage(String username, int id) {
    MapStageDef stageDef = AssetsIO.getStage(id);
    try {
      if(stageDef == null) {
        stageDef = AssetsIO.saveStages(map.getMapStageAll(username)).get(id);
      }
    } catch(Exception e) {
      e.printStackTrace();
      return "未知关卡" + id;
    }
    return stageDef.getName();
  }
}
