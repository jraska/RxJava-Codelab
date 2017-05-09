package com.jraska.rx.codelab.http;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class HttpModule {
  public static GitHubApi gitHubApi() {
    return gitHubRetrofit().create(GitHubApi.class);
  }

  public static HttpBinApi httpBinApi() {
    return httpBinRetrofit().create(HttpBinApi.class);
  }

  private static Retrofit gitHubRetrofit() {
    return retrofitBuilder()
      .baseUrl("https://api.github.com")
      .build();
  }

  private static Retrofit httpBinRetrofit() {
    return retrofitBuilder()
      .baseUrl("http://httpbin.org")
      .build();
  }

  private static Retrofit.Builder retrofitBuilder() {
    return new Retrofit.Builder()
      .validateEagerly(true)
      .client(okHttpClient())
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
  }

  private static OkHttpClient okHttpClient() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(System.out::println)
      .setLevel(Level.BASIC);

    return new OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .addInterceptor(new MockResponsesInterceptor())
      .build();
  }

  private HttpModule() {
    throw new AssertionError("No instances");
  }
}
