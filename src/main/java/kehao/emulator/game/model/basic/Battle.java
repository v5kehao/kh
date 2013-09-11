package kehao.emulator.game.model.basic;

import java.util.List;

public abstract class Battle<T extends BattleExtData> extends EnergyUser {
  public static final int BattleWin = 1;
  public static final int BattleLost = 2;
  public static final int BattleResultPending = 0;

  private String BattleId;
  private int Win;
  private T ExtData;
  private String[] prepare;
  private Player AttackPlayer;
  private Player DefendPlayer;
  private List<BattleRound> Battle;

  public boolean win() {
    return Win == BattleWin;
  }

  public boolean lost() {
    return Win == BattleLost;
  }

  public boolean requireManualControl() {
    return Win == BattleResultPending;
  }

  public String getBattleId() {
    return BattleId;
  }

  public void setBattleId(String battleId) {
    BattleId = battleId;
  }

  public int getWin() {
    return Win;
  }

  public void setWin(int win) {
    Win = win;
  }

  public T getExtData() {
    return ExtData;
  }

  public void setExtData(T extData) {
    ExtData = extData;
  }

  public String[] getPrepare() {
    return prepare;
  }

  public void setPrepare(String[] prepare) {
    this.prepare = prepare;
  }

  public Player getAttackPlayer() {
    return AttackPlayer;
  }

  public void setAttackPlayer(Player attackPlayer) {
    AttackPlayer = attackPlayer;
  }

  public Player getDefendPlayer() {
    return DefendPlayer;
  }

  public void setDefendPlayer(Player defendPlayer) {
    DefendPlayer = defendPlayer;
  }

  public List<BattleRound> getBattle() {
    return Battle;
  }

  public void setBattle(List<BattleRound> battle) {
    Battle = battle;
  }
}
