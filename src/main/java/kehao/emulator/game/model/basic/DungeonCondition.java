package kehao.emulator.game.model.basic;

public class DungeonCondition {
  private int ConditionId;
  private int Type;
  private int Value;
  private String Content;
  private String Marks;
  private int Layer;

  public int getConditionId() {
    return ConditionId;
  }

  public int getType() {
    return Type;
  }

  public int getValue() {
    return Value;
  }

  public String getContent() {
    return Content;
  }

  public String getMarks() {
    return Marks;
  }

  public int getLayer() {
    return Layer;
  }
}
