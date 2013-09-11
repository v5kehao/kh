package kehao.util;

import java.math.BigInteger;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class Client {

  private BigInteger overrideRandom;
  private String key;

  public Client() {
  }

  public Client(BigInteger overrideRandom, String key) {
    this.overrideRandom = overrideRandom;
    this.key = key;
  }

  public String encryptArgs(String data, int t, int n, int r) {
    if(t >= n) {
      data = XXTEA.encrypt(data, key);
    }
    if(t >= r) {
      data = Encoder.btoa(data);
    }
    return data;
  }

  public String idEncrypt(String data) {
    String id = "3f52f005e3f0b506b8e63a504b784f3e";
    int idLength = id.length();
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < data.length(); i++) {
      int o = i % idLength;
      sb.append(StringHelper.fromCharCode(data.charAt(i) ^ id.charAt(o)));
    }
    return sb.toString();
  }

  public String generateKey(String data) {
    if(overrideRandom != null) return generateKey(data, overrideRandom);
    return generateKey(data, new BigInteger(127, new Random()));
  }

  public String generateKey(String data, BigInteger s) {
    data = idEncrypt(Encoder.atob(data));
    Map dataMap = Unserializer.UnserializeMap(data);
    BigInteger n = new BigInteger((String)dataMap.get("p"));
    BigInteger r = new BigInteger((String)dataMap.get("g"));
    BigInteger i = new BigInteger((String)dataMap.get("y"));
    BigInteger o = i.modPow(s, n);
    String oString = BigIntegerHelper.BigIntegerToString(o);
    int f = 16 - oString.length();
    String[] l = new String[f + 1];
    for(int h = 0; h < f; h++) {
      l[h] = "\0";
    }
    l[f] = oString;
    key = StringUtils.join(l);
    String p = r.modPow(s, n).toString();
    p = idEncrypt(p);
    return Encoder.btoa(p);
  }

  public String getKey() {
    return key;
  }

}
