package kehao.emulator.game.model.basic;

import java.util.List;

public class MapStageDef {
  public static final int NormalLevel = 1;
  public static final int BossLevel = 2;
  public static final int SecretLevel = 0;
  public static final int MazeLevel = 3;

  private int MapStageDetailId;
  private String Name;
  private int Type;
  private int Rank;
  private int X;
  private int Y;
  private int Prev;
  private int Next;
  private int NextBranch;
  private String FightName;
  private String FightImg;
  private List<Dialogue> Dialogue;
  private List<Dialogue> DialogueAfter;
  private List<Level> Levels;

  public int getMapStageDetailId() {
    return MapStageDetailId;
  }

  public String getName() {
    return Name;
  }

  public int getType() {
    return Type;
  }

  public int getRank() {
    return Rank;
  }

  public int getX() {
    return X;
  }

  public int getY() {
    return Y;
  }

  public int getPrev() {
    return Prev;
  }

  public int getNext() {
    return Next;
  }

  public int getNextBranch() {
    return NextBranch;
  }

  public String getFightName() {
    return FightName;
  }

  public String getFightImg() {
    return FightImg;
  }

  public List<Dialogue> getDialogue() {
    return Dialogue;
  }

  public List<Dialogue> getDialogueAfter() {
    return DialogueAfter;
  }

  public List<Level> getLevels() {
    return Levels;
  }

  @Override
  public String toString() {
    return "MapStage " + MapStageDetailId + " " + Name;
  }
}
