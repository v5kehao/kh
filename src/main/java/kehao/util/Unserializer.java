package kehao.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

public class Unserializer {

  public static final String StringPattern = "s:(\\d+):\"([\\w\\W]*)\";";
  public static final String ArrayPattern = "a:(\\d+):\\{([^}]*)}";
  public static final String ValuePairPattern = "%([^%]*)%%([^%]*)%";

  public static List<Object> Unserialize(String string) {
    Map<Object, Object> refs = new HashMap<Object, Object>();
//    string = string.replace("{\"", "{'").replace("\":", "':").replace(":\"", ":'").replace("\",", "',").replace(",\"", ",'").replace("\"}", "'}");
    Pattern sp = Pattern.compile(StringPattern);
    int length;
    Matcher sm;
    String block, value, hash;
    while((sm = sp.matcher(string)).find()) {
      block = sm.group(0);
      length = Integer.parseInt(sm.group(1));
      value = sm.group(2);
      if(length < value.length()) {
        int diff = value.length() - length;
        block = block.substring(0, block.length() - diff);
        value = value.substring(0, length);
      }
      hash = Integer.toHexString(block.hashCode());
      refs.put(hash, value);
      string = string.replace(block, '%' + hash + '%');
    }
    Pattern ap = Pattern.compile(ArrayPattern);
    Pattern vpp = Pattern.compile(ValuePairPattern);
    Matcher apm, vppm;
    Map<Object, Object> arrayMap;
    String pairKey, pairValue;
    while((apm = ap.matcher(string)).find()) {
      block = apm.group(0);
      length = Integer.parseInt(apm.group(1));
      value = apm.group(2);
      arrayMap = new LinkedHashMap<Object, Object>();
      hash = Integer.toHexString(block.hashCode());
      refs.put(hash, arrayMap);
      string = string.replace(block, '%' + hash + '%');
      while((vppm = vpp.matcher(value)).find()) {
        block = vppm.group(0);
        pairKey = vppm.group(1);
        pairValue = vppm.group(2);
        arrayMap.put(refs.get(pairKey), refs.get(pairValue));
        refs.remove(pairKey);
        refs.remove(pairValue);
        value = value.replace(block, "");
      }
    }
    return new ArrayList<Object>(refs.values());
  }

  public static String UnserializeString(String string) {
    List<Object> list = Unserialize(string);
    for(Object o : list) {
      if(o instanceof String) {
        return (String)o;
      }
    }
    return null;
  }

  public static Map UnserializeMap(String string) {
    List<Object> list = Unserialize(string);
    for(Object o : list) {
      if(o instanceof Map) {
        return (Map)o;
      }
    }
    return null;
  }

  public static LinkedHashMap JsonToMap(String string) {
    return new Gson().fromJson(string, LinkedHashMap.class);
  }
}
