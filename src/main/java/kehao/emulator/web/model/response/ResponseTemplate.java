package kehao.emulator.web.model.response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kehao.util.StringHelper;
import kehao.util.Unserializer;
import kehao.util.XXTEA;
import org.apache.commons.lang3.StringEscapeUtils;

public class ResponseTemplate {

  public static final String ResultPattern = "muhe_result=\"([^\"]*)\";";
  public static final String ErrnoPattern = "muhe_errno=\"([^\"]*)\";";
  public static final String ErrstrPattern = "muhe_errstr=\"([^\"]*)\";";
  public static final String OutputPattern = "muhe_output=\"([^\"]*)\";";

  private String result;
  private String errno;
  private String errstr;
  private String output;

  public ResponseTemplate() {
  }

  public ResponseTemplate(String responseString, String key) {
    Matcher resultMatcher = Pattern.compile(ResultPattern).matcher(responseString);
    if(resultMatcher.find()) result = resultMatcher.group(1);
    Matcher errnoMatcher = Pattern.compile(ErrnoPattern).matcher(responseString);
    if(errnoMatcher.find()) errno = errnoMatcher.group(1);
    Matcher errstrMatcher = Pattern.compile(ErrstrPattern).matcher(responseString);
    if(errstrMatcher.find()) errstr = errstrMatcher.group(1);
    Matcher outputMatcher = Pattern.compile(OutputPattern).matcher(responseString);
    if(outputMatcher.find()) output = outputMatcher.group(1);

    result = StringEscapeUtils.unescapeJava(result.replace("\\x", "\\u00"));
    String decryptedResult = XXTEA.decrypt(result, key);
    result = Unserializer.UnserializeString(StringHelper.toUTF16(decryptedResult));
    result = StringEscapeUtils.unescapeJava(result.replace("\"[", "[").replace("]\"", "]"));
  }

  public void setResult(String result) {
    this.result = result;
  }

  public void setErrno(String errno) {
    this.errno = errno;
  }

  public void setErrstr(String errstr) {
    this.errstr = errstr;
  }

  public void setOutput(String output) {
    this.output = output;
  }

  public String getResult() {
    return result;
  }

  public String getErrno() {
    return errno;
  }

  public String getErrstr() {
    return errstr;
  }

  public String getOutput() {
    return output;
  }

  public boolean badRequest() {
    return errno != null && !errno.equals("0");
  }
}
