package kehao.emulator.game.model.basic;

public class Chip {

  public static final int FROM_STAGE = 1;
  public static final int FROM_MAZE = 2;
  public static final int FROM_FREE_FIGHT = 3;

  private int id;
  private int num;
  private int type;

  public boolean obtained() {
    return num == 1;
  }

  public boolean isFromStage() {
    return type == FROM_STAGE;
  }

  public boolean isFromMaze() {
    return type == FROM_MAZE;
  }

  public boolean isFromFreeFight() {
    return type == FROM_FREE_FIGHT;
  }

  public int getId() {
    return id;
  }

  public int getNum() {
    return num;
  }

  public int getType() {
    return type;
  }

}
