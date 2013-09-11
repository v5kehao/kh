package kehao.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextReport implements AppendableReport {

  private BufferedWriter writer;
  private Map<String, StringBuilder> buffers;
  private boolean requireDestroy = false;

  public TextReport(String file) throws IOException {
    writer = new BufferedWriter(new FileWriter(file, true));
    buffers = new HashMap<>();
  }

  @Override
  public AppendableReport append(String src, String record) {
    try {
      StringBuilder sb = buffers.get(src);
      if(sb == null) {
        sb = new StringBuilder().append("\n----------------------------------------");
        buffers.put(src, sb);
      }
      sb.append(DateStringUtils.time()).append("\t").append(record).append("\n");
    } catch(Exception e) {
      e.printStackTrace();
    }
    return this;
  }

  @Override
  public void finish(String src) {
    StringBuilder sb = buffers.get(src);
    if(sb != null) {
      String report = sb.append("----------------------------------------\n").toString();
      buffers.remove(src);
      try {
        writer.append(report);
        writer.flush();
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
    if(requireDestroy) tryDestroy();
  }

  @Override
  public void destroy() {
    try {
      for(StringBuilder sb : buffers.values()) {
        writer.append(sb.append("**************用户强行终止**************\n").toString());
      }
      writer.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void tryDestroy() {
    if(buffers.isEmpty()) {
      destroy();
    }
  }

  public void requireDestroy() {
    requireDestroy = true;
    tryDestroy();
  }
}
