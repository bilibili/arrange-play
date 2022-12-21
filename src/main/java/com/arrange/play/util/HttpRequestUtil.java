package com.arrange.play.util;

import java.io.IOException;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Slf4j
@Component
public class HttpRequestUtil {

  private static OkHttpClient okHttpClient;

  private HttpRequestUtil() {
    okHttpClient = SSlUtil.getUnsafeOkHttpClient();
  }

  public static boolean isResponseSuccess(Response response) {
    return response != null && response.isSuccessful() && response.body() != null;
  }

  public static Response getRequest(String url, String params, Map<String, String> headers) {
    if (StringsUtils.isNotEmptyOrNull(params)) {
      url = url + "?" + params;
    }
    Request.Builder builder = new Request.Builder().url(url);
    addHeaders(builder, headers);
    Request request = builder.build();
    Response response = null;
    try {
      response = okHttpClient.newCall(request).execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }

  public static Response formPostRequest(String url, Map<String, String> map) {
    return formPostRequest(url, map, null);
  }

  public static Response formPostRequest(String url, Map<String, String> map, Map<String, String> headers) {
    FormBody.Builder formBody = new FormBody.Builder();
    if (map != null && map.size() > 0) {
      map.forEach(formBody::add);
    }

    RequestBody requestBody = formBody.build();
    return postRequest(url, requestBody, headers);
  }

  public static Response jsonPostRequest(String url, String jsonString) {
    return jsonPostRequest(url, jsonString, null);
  }

  public static Response jsonPostRequest(String url, String jsonString, Map<String, String> headers) {
    if (StringsUtils.isEmptyOrNull(jsonString)) {
      jsonString = "{}";
    }
    MediaType json = MediaType.parse("application/json; charset=utf-8");
    RequestBody requestBody = RequestBody.create(json, jsonString);
    return postRequest(url, requestBody, headers);
  }

  private static Response postRequest(String url, RequestBody requestBody, Map<String, String> headers) {
    Request.Builder builder = new Request.Builder()
        .url(url)
        .post(requestBody);
    addHeaders(builder, headers);

    Request request = builder.build();
    Response response = null;
    try {
      response = okHttpClient.newCall(request).execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }

  private static void addHeaders(Request.Builder builder, Map<String, String> headers) {
    if (CollectionUtils.isEmpty(headers)) {
      return;
    }

    headers.keySet().forEach(key -> builder.addHeader(key, headers.get(key)));
  }


}

class SSlUtil {

  /**
   * okHttp3添加信任所有证书
   *
   * @return OkHttpClient
   */
  public static OkHttpClient getUnsafeOkHttpClient() {
    try {
      final TrustManager[] trustAllCerts = new TrustManager[]{
          new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                String authType) {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                String authType) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
              return new java.security.cert.X509Certificate[]{};
            }
          }
      };
      final SSLContext sslContext = SSLContext.getInstance("SSL");
      sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
      final javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
      OkHttpClient.Builder builder = new OkHttpClient.Builder();
      // jdk9+ 弃用 builder.sslSocketFactory(sslSocketFactory);
      builder.sslSocketFactory(sslSocketFactory, getX509TrustManager());
      builder.readTimeout(60, TimeUnit.SECONDS);
      builder.writeTimeout(60, TimeUnit.SECONDS);
      builder.connectTimeout(60, TimeUnit.SECONDS);
      builder.hostnameVerifier((hostname, session) -> true);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * jdk9+ 构造 X509TrustManager
   *
   * @return X509TrustManager
   */
  private static X509TrustManager getX509TrustManager() {
    X509TrustManager trustManager = null;
    try {
      TrustManagerFactory trustManagerFactory = TrustManagerFactory
          .getInstance(TrustManagerFactory.getDefaultAlgorithm());
      trustManagerFactory.init((KeyStore) null);
      TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
      if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
        throw new IllegalStateException(
            "Unexpected default trust managers:" + Arrays.toString(trustManagers));
      }
      trustManager = (X509TrustManager) trustManagers[0];
    } catch (Exception e) {
      e.printStackTrace();
    }

    return trustManager;
  }
}
