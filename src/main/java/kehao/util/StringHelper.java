package kehao.util;

import org.apache.commons.lang3.StringUtils;

public class StringHelper {

  public static String fromCharCode(int... codePoints) {
    return new String(codePoints, 0, codePoints.length);
  }

  public static String toUTF8(String str) {
    int len = str.length();
    String[] out = new String[len];
    int i, j;
    char c, c2;
    for(i = 0, j = 0; i < len; i++, j++) {
      c = str.charAt(i);
      if(c <= 0x7f) {
        out[j] = String.valueOf(str.charAt(i));
      } else if(c <= 0x7ff) {
        out[j] = fromCharCode(0xc0 | (c >>> 6), 0x80 | (c & 0x3f));
      } else if(c < 0xd800 || c > 0xdfff) {
        out[j] = fromCharCode(0xe0 | (c >>> 12), 0x80 | ((c >>> 6) & 0x3f), 0x80 | (c & 0x3f));
      } else {
        if(++i < len) {
          c2 = str.charAt(i);
          if(c <= 0xdbff && 0xdc00 <= c2 && c2 <= 0xdfff) {
            c = (char)(((c & 0x03ff) << 10 | (c2 & 0x03ff)) + 0x010000);
            if(0x010000 <= c && c <= 0x10ffff) {
              out[j] = fromCharCode(0xf0 | ((c >>> 18) & 0x3f), 0x80 | ((c >>> 12) & 0x3f), 0x80 | ((c >>> 6) & 0x3f), 0x80 | (c & 0x3f));
            } else {
              out[j] = "?";
            }
          } else {
            i--;
            out[j] = "?";
          }
        } else {
          i--;
          out[j] = "?";
        }
      }
    }
    return StringUtils.join(out);
  }

  public static String toUTF16(String str) {
    int len = str.length();
    String[] out = new String[len];
    int i = 0;
    int j = 0;
    int s;
    char c, c2, c3, c4;
    while(i < len) {
      c = str.charAt(i++);
      switch(c >> 4) {
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
          out[j++] = String.valueOf(str.charAt(i - 1));
          break;
        case 12:
        case 13:
          c2 = str.charAt(i++);
          out[j++] = fromCharCode(((c & 0x1f) << 6) | (c2 & 0x3f));
          break;
        case 14:
          c2 = str.charAt(i++);
          c3 = str.charAt(i++);
          out[j++] = fromCharCode(((c & 0x0f) << 12) | ((c2 & 0x3f) << 6) | (c3 & 0x3f));
          break;
        case 15:
          switch(c & 0xf) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
              c2 = str.charAt(i++);
              c3 = str.charAt(i++);
              c4 = str.charAt(i++);
              s = ((c & 0x07) << 18) | ((c2 & 0x3f) << 12) | ((c3 & 0x3f) << 6) | (c4 & 0x3f) - 0x10000;
              if(0 <= s && s <= 0xfffff) {
                out[j++] = fromCharCode(((s >>> 10) & 0x03ff) | 0xd800, (s & 0x03ff) | 0xdc00);
              } else {
                out[j++] = "?";
              }
              break;
            case 8:
            case 9:
            case 10:
            case 11:
              i += 4;
              out[j++] = "?";
              break;
            case 12:
            case 13:
              i += 5;
              out[j++] = "?";
              break;
          }
      }
    }
    return StringUtils.join(out);
  }
}
