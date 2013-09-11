package kehao.util;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

public class BigIntegerHelper {

  private static int[] mul(int[] a, int[] b) {
    int n = a.length;
    int m = b.length;
    int nm = n + m;
    int i, j;
    int[] c = new int[nm];
    for(i = 0; i < nm; i++) c[i] = 0;
    for(i = 0; i < n; i++) {
      for(j = 0; j < m; j++) {
        c[i + j] += a[i] * b[j];
        c[i + j + 1] += (c[i + j] >> 16) & 0xffff;
        c[i + j] &= 0xffff;
      }
    }
    return c;
  }

  private static  int[] dec2num(String str) {
    int n = str.length();
    int[] a = new int[]{0};
    int[] tmp;
    int i, j, m, start;
    n += 4 - (n % 4);
    str = zerofill(str, n);
    n >>= 2;
    for(i = 0; i < n; i++) {
      a = mul(a, new int[]{10000});
      m = a.length;
      tmp = new int[n];
      System.arraycopy(a, 0, tmp, 0, a.length);
      a = tmp;
      start = i << 2;
      a[0] += Integer.valueOf(str.substring(start, start + 4));
      j = a[m] = 0;
      while(j < m && a[j] > 0xffff) {
        a[j] &= 0xffff;
        j++;
        a[j]++;
      }
      int realLength = a.length - 1;
      while(realLength > 1 && a[realLength - 1] == 0) {
        realLength--;
      }
      if(realLength != a.length) {
        tmp = new int[realLength];
        System.arraycopy(a, 0, tmp, 0, realLength);
        a = tmp;
      }
    }
    return a;
  }

  private static String zerofill(Object str, int num) {
    int n = num - str.toString().length();
    int i;
    String s = "";
    for(i = 0; i < n; i++) s += '0';
    return s + str;
  }

  private static String num2str(int[] num) {
    int n = num.length;
    String[] s = new String[n];
    for(int i = 0; i < n; i++) {
      s[n - i - 1] = StringHelper.fromCharCode(num[i] >> 8 & 0xff, num[i] & 0xff);
    }
    return StringUtils.join(s);
  }

  public static String BigIntegerToString(BigInteger bigInteger) {
    return num2str(dec2num(bigInteger.toString()));
  }



}
