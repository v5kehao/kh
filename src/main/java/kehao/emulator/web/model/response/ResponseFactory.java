package kehao.emulator.web.model.response;

import com.google.gson.Gson;

public class ResponseFactory {

  public static <T extends ReturnTemplate> T getResponse(String str, String key, Class<T> clazz) {
    try {
      String result = new ResponseTemplate(str, key).getResult();
      return new Gson().fromJson(result, clazz);
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
