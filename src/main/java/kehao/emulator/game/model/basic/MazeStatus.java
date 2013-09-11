package kehao.emulator.game.model.basic;

public class MazeStatus {
  private String Name;
  private int Layer;
  private int Clear;
  private int FreeReset;
  private int ResetCash;

  public boolean isMazeClear() {
    return Clear != 0;
  }

  public void clear() {
    Clear = 1;
  }

  public boolean allowFreeReset() {
    return FreeReset != 0;
  }

  public String getName() {
    return Name;
  }

  public int getLayer() {
    return Layer;
  }

  public void setLayer(int layer) {
    Layer = layer;
  }

  public int getClear() {
    return Clear;
  }

  public int getFreeReset() {
    return FreeReset;
  }

  public int getResetCash() {
    return ResetCash;
  }
}
