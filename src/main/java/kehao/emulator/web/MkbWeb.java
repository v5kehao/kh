package kehao.emulator.web;

import java.util.ArrayList;
import java.util.List;

import kehao.emulator.web.model.request.PassportRequest;
import kehao.emulator.web.model.response.EncryptKeyResponse;
import kehao.emulator.web.model.response.ResponseFactory;
import kehao.emulator.web.model.response.ReturnTemplate;
import kehao.util.Client;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class MkbWeb {

  public static final int EncryptMode = 2;

  private DefaultHttpClient httpClient;
  private Client client;
  private String token;
  private EncryptKeyResponse encryptKeyResponse;

  public MkbWeb(DefaultHttpClient httpClient) {
    this.httpClient = httpClient;
    client = new Client();
    token = "js" + (long)Math.floor(Math.random() * System.currentTimeMillis());
  }

  public void requestEncryptKey() {
    HttpGet get = new HttpGet("http://pp.fantasytoyou.com/pp/userService.do?muhe_id=" + token + "&muhe_encode=false&muhe_encrypt=true");
    try {
      HttpResponse response = httpClient.execute(get);
      BasicResponseHandler handler = new BasicResponseHandler();
      String responseString = handler.handleResponse(response);
      encryptKeyResponse = new EncryptKeyResponse(responseString);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public boolean proposeCounterKey() {
    String key = encryptKeyResponse.getKey();
    String counterKey = client.generateKey(key);
    HttpGet get = new HttpGet("http://pp.fantasytoyou.com/pp/userService.do?muhe_id=" + token + "&muhe_encode=false&muhe_encrypt=" + counterKey);
    try {
      HttpResponse response = httpClient.execute(get);
      BasicResponseHandler handler = new BasicResponseHandler();
      String responseString = handler.handleResponse(response);
      return true;
    } catch(Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public <T extends ReturnTemplate> T sendRequest(PassportRequest<T> passportRequest, Class<T> clazz) {
    HttpPost post = new HttpPost("http://pp.fantasytoyou.com/pp/userService.do?muhe_id=" + token);
    try {
      List<NameValuePair> nvps = new ArrayList<>();
      nvps.add(new BasicNameValuePair("muhe_func", passportRequest.getFunc()));
      nvps.add(new BasicNameValuePair("muhe_args", client.encryptArgs(passportRequest.getArgs(), EncryptMode, 1, 0)));
      nvps.add(new BasicNameValuePair("muhe_encode", "false"));
      nvps.add(new BasicNameValuePair("muhe_encrypt", Integer.toString(EncryptMode)));
      nvps.add(new BasicNameValuePair("muhe_ref", "false"));

      post.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

      HttpResponse response = httpClient.execute(post);

      BasicResponseHandler handler = new BasicResponseHandler();
      String responseString = handler.handleResponse(response);

      return ResponseFactory.getResponse(responseString, client.getKey(), clazz);
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
