package kehao.emulator.game;

import java.io.IOException;
import java.util.*;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.*;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class MkbGame {

  private String phpp;
  private String phpl;
  private String pvc;
  private String pvb;

  private String host;
  private DefaultHttpClient dedicateHttpClient;

  public MkbGame(String host, DefaultHttpClient dedicateHttpClient, String phpp, String phpl, String pvc, String pvb) {
    this.host = host;
    this.dedicateHttpClient = dedicateHttpClient;
    this.phpp = phpp;
    this.phpl = phpl;
    this.pvc = pvc;
    this.pvb = pvb;
  }

  public String doAction(String service, String action, Map<String, String> params) {
    return doAction(service, action, params, dedicateHttpClient);
  }

  public String doAction(String service, String action, Map<String, String> params, DefaultHttpClient httpClient) {
    String url;
    if(action != null) {
      url = host + service + "?do=" + action + "&phpp=" + phpp + "&phpl=" + phpl + "&pvc=" + pvc + "&pvb=" + pvb;
    } else {
      url = host + service + "?phpp=" + phpp + "&phpl=" + phpl + "&pvc=" + pvc + "&pvb=" + pvb;
    }
    HttpPost post = new HttpPost(url);
    if(params != null) {
      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
      Set<Map.Entry<String, String>> entrySet = params.entrySet();
      for(Map.Entry<String, String> entry : entrySet) {
        nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
      }
      post.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
    }
    try {
      httpClient.addResponseInterceptor(new HttpResponseInterceptor() {

        public void process(final HttpResponse response, final HttpContext context) throws HttpException, IOException {
          HttpEntity entity = response.getEntity();
          if(entity != null) {
            Header ceheader = entity.getContentEncoding();
            if(ceheader != null) {
              HeaderElement[] codecs = ceheader.getElements();
              for(HeaderElement codec : codecs) {
                if(codec.getName().equalsIgnoreCase("gzip")) {
                  response.setEntity(new GzipDecompressingEntity(response.getEntity()));
                  return;
                }
              }
            }
          }
        }

      });
      HttpResponse response = httpClient.execute(post);
      HttpEntity entity = response.getEntity();
      String content = StringEscapeUtils.unescapeJava(EntityUtils.toString(entity));
      if(content == null || content.startsWith("<")) {
        return null;
      }
      return content;
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
