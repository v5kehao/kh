package kehao.util;

import kehao.emulator.game.model.basic.CardDef;
import kehao.emulator.game.model.basic.RuneDef;
import kehao.emulator.game.model.basic.SkillDef;

public interface AssetsOracle {
  public CardDef getCardDef(String username, int id);
  public SkillDef getSkillDef(String username, int id);
  public RuneDef getRuneDef(String username, int id);
}
