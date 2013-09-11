package kehao.util;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class XXTEA {
  private static int delta = 0x9E3779B9;

  public static String intArrayToString(int[] data, boolean includeLength) {
    int length = data.length;
    int n = (length - 1) << 2;
    if(includeLength) {
      int charNumber = data[length - 1];
      if(charNumber < n - 3 || charNumber > n) {
        return null;
      }
      n = charNumber;
    }
    String[] resultArray = new String[length];
    for(int i = 0; i < length; i++) {
      resultArray[i] = StringHelper.fromCharCode(data[i] & 0xff,
                                                  data[i] >>> 8 & 0xff,
                                                  data[i] >>> 16 & 0xff,
                                                  data[i] >>> 24 & 0xff);
    }
    String result = StringUtils.join(resultArray);
    if(includeLength) {
      return result.substring(0, n);
    }
    return result;
  }

  public static int[] stringToIntArray(String string, boolean includeLength) {
    int length = string.length();
    int intArrayLength = (int)Math.ceil((double)length / 4);
    int[] result = new int[includeLength ? intArrayLength + 1 : intArrayLength];
    int i = 0, intTemp;
    while(i < length) {
      intTemp = string.charAt(i++);
      if(i++ < length) intTemp = intTemp | string.charAt(i - 1) << 8;
      if(i++ < length) intTemp = intTemp | string.charAt(i - 1) << 16;
      if(i++ < length) intTemp = intTemp | string.charAt(i - 1) << 24;
      result[(i >> 2) - 1] = intTemp;
    }
    if(includeLength) {
      result[intArrayLength] = length;
    }
    return result;
  }

  public static String encrypt(String string, String key) {
    if(string.isEmpty()) {
      return "";
    }
    int[] v = stringToIntArray(string, true);
    int[] k = stringToIntArray(key, false);
    if(k.length < 4) {
      int[] kTemp = new int[4];
      Arrays.fill(kTemp, 0);
      System.arraycopy(k, 0, kTemp, 0, k.length);
      k = kTemp;
    }
    int n = v.length - 1;
    int z = v[n], y, mx;
    int e, p, q = (int)Math.floor((6d + 52d / ((double)n + 1d))), sum = 0;
    while(0 < q--) {
      sum = sum + delta;
      e = sum >>> 2 & 3;
      for(p = 0; p < n; p++) {
        y = v[p + 1];
        mx = (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
        z = v[p] = v[p] + mx;
      }
      y = v[0];
      mx = (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
      z = v[n] = v[n] + mx;
    }
    return intArrayToString(v, false);
  }

  public static String decrypt(String string, String key) {
    if(string.isEmpty()) {
      return "";
    }
    int[] v = stringToIntArray(string, false);
    int[] k = stringToIntArray(key, false);
    if(k.length < 4) {
      int[] kTemp = new int[4];
      Arrays.fill(kTemp, 0);
      System.arraycopy(k, 0, kTemp, 0, k.length);
      k = kTemp;
    }
    int n = v.length - 1;
    int z, y = v[0], mx;
    int e, p, q = (int)Math.floor(6d + 52d / (n + 1)), sum = q * delta;
    while(sum != 0) {
      e = sum >>> 2 & 3;
      for(p = n; p > 0; p--) {
        z = v[p - 1];
        mx = (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
        y = v[p] = v[p] - mx;
      }
      z = v[n];
      mx = (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
      y = v[0] = v[0] - mx;
      sum = sum - delta;
    }
    return intArrayToString(v, true);
  }
}
