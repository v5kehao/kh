package kehao.util;

public interface AppendableReport {
  public AppendableReport append(String src, String record);
  public void finish(String src);
  public void destroy();
}
