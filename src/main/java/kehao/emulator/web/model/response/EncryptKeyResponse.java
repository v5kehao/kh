package kehao.emulator.web.model.response;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncryptKeyResponse {
  public static final String KeyPattern = "muhe_encrypt=\"([^\"]*)\";";
  public static final String UrlPattern = "muhe_url=\"([^\"]*)\";";
  public static final String KeylenPattern = "keylen=\"([^\"]*)\";";

  private String key;
  private String url;
  private int keylen;
  private Map<String, String> extraProperties = new HashMap<String, String>();

  public EncryptKeyResponse(String responseString) {
    Matcher keyMatcher = Pattern.compile(KeyPattern).matcher(responseString);
    if(keyMatcher.find()) key = keyMatcher.group(1);
    Matcher keylenMatcher = Pattern.compile(KeylenPattern).matcher(responseString);
    if(keylenMatcher.find()) keylen = Integer.parseInt(keylenMatcher.group(1));
    Matcher urlMatcher = Pattern.compile(UrlPattern).matcher(responseString);
    if(urlMatcher.find()) url = urlMatcher.group(1);
    if(url != null && url.contains(";")) {
      int urlBreakIndex = url.indexOf(";");
      String tail = url.substring(urlBreakIndex);
      url = url.substring(0, urlBreakIndex);
      Pattern propertyPattern = Pattern.compile(";?([^=]*)=([^;]*);?");
      Matcher propertyMatcher = propertyPattern.matcher(tail);
      while(propertyMatcher.find()) {
        extraProperties.put(propertyMatcher.group(1), propertyMatcher.group(2));
      }
    }
  }

  public void setKey(String key) {
    this.key = key;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setKeylen(int keylen) {
    this.keylen = keylen;
  }

  public String getKey() {
    return key;
  }

  public String getUrl() {
    return url;
  }

  public int getKeylen() {
    return keylen;
  }

  public String get(String key) {
    return extraProperties.get(key);
  }
}
