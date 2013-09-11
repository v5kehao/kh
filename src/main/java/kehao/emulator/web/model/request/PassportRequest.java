package kehao.emulator.web.model.request;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import kehao.emulator.web.model.response.ReturnTemplate;

public abstract class PassportRequest<T extends ReturnTemplate> {

  protected Map<String, String> argMap = new LinkedHashMap<String, String>();

  public abstract String getFunc();

  public String getArgs() {
    StringBuilder sb = new StringBuilder();
    Set<Map.Entry<String, String>> es = argMap.entrySet();
    for(Map.Entry<String, String> e : es) {
      if(sb.length() != 0) {
        sb.append(',');
      }
      sb.append('"').append(e.getKey()).append('"').append(':').append('"').append(e.getValue()).append('"');
    }
    String str = '{' + sb.toString() + '}';
    return "a:1:{i:0;s:" + str.length() + ":\"" + str + "\";}";
  }
}
