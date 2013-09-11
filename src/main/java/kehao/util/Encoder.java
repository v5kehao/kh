package kehao.util;

import org.apache.commons.codec.binary.Base64;

public class Encoder {
  public static String btoa(String value) {
    try {
      return Base64.encodeBase64String(value.getBytes("ISO-8859-1"));
    } catch(Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String atob(String value) {
    return new String(Base64.decodeBase64(value));
  }
}
