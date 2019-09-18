package com.jraska.rx.codelab.http;

import com.jraska.rx.codelab.Utils;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class HttpModule {
  private static Dispatcher dispatcher = new Dispatcher();

  public static GitHubApi mockedGitHubApi() {
    return gitHubRetrofitBuilder()
      .client(okClientBuilder()
        .addInterceptor(new MockResponsesInterceptor())
        .build())
      .build()
      .create(GitHubApi.class);
  }

  public static GitHubApi gitHubApi() {
    return gitHubRetrofitBuilder()
      .client(okClientBuilder().build())
      .build()
      .create(GitHubApi.class);
  }

  public static HttpBinApi httpBinApi() {
    return httpBinRetrofit().create(HttpBinApi.class);
  }

  private static Retrofit.Builder gitHubRetrofitBuilder() {
    return new Retrofit.Builder()
      .baseUrl("https://api.github.com")
      .validateEagerly(true)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
  }

  private static Retrofit httpBinRetrofit() {
    return new Retrofit.Builder()
      .baseUrl("http://httpbin.org")
      .validateEagerly(true)
      .client(okClientBuilder().build())
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build();
  }

  private static OkHttpClient.Builder okClientBuilder() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(System.out::println)
      .setLevel(Level.BASIC);

    return new OkHttpClient.Builder()
      .dispatcher(dispatcher)
      .addInterceptor(loggingInterceptor);
  }

  public static void awaitNetworkRequests() {
    Utils.sleep(25); // There is a small delay during thread hopping in Rx
    while (dispatcher.runningCallsCount() > 0) {
      Utils.sleep(25);
    }

    Utils.sleep(25); // To allow consuming results, There is a small delay during thread hopping in Rx
  }

  private HttpModule() {
    throw new AssertionError("No instances");
  }
}
