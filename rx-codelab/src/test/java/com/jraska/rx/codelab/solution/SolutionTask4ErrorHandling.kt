package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.RxLogging;
import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import io.reactivex.internal.functions.Functions;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;

public class Solution_Task4_ErrorHandling {
  private HttpBinApi httpBinApi = HttpModule.httpBinApi();

  @Before
  public void before() {
    RxLogging.INSTANCE.enableObservableSubscribeLogging();
  }

  @Test
  public void printErrorMessage() {
    httpBinApi.failingGet()
      .subscribe(System.out::println, System.err::println);
  }

  @Test
  public void onErrorReturnItem_emitCustomItemOnError() {
    httpBinApi.failingGet()
      .onErrorReturnItem(syntheticBody())
      .subscribe(System.out::println);
  }

  @Test
  public void onErrorResumeNext_subscribeToExtraObservableOnError() {
    httpBinApi.failingGet()
      .onErrorResumeNext(httpBinApi.backupGet())
      .subscribe(System.out::println, Functions.emptyConsumer());
  }

  @Test
  public void retry_retryOnError() {
    httpBinApi.flakyGet()
      .retry()
      .subscribe(System.out::println);
  }

  static ResponseBody syntheticBody() {
    return ResponseBody.create(MediaType.get("application/json"), "{}");
  }
}
