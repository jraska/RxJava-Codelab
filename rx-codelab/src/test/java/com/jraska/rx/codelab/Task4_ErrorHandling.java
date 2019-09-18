package com.jraska.rx.codelab;

import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import io.reactivex.internal.functions.Functions;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;

public class Task4_ErrorHandling {
  private HttpBinApi httpBinApi = HttpModule.httpBinApi();

  @Before
  public void before() {
    RxLogging.enableObservableSubscribeLogging();
  }

  @Test
  public void printErrorMessage() {
    // TODO: Subscribe and incoming error message - httpBinApi.failingGet(), subscribe() with 2 parameters
  }

  @Test
  public void onErrorReturnItem_emitCustomItemOnError() {
    // TODO: When an error happens, emit syntheticBody(), httpBinApi.failingGet()
  }

  @Test
  public void onErrorResumeNext_subscribeToExtraObservableOnError() {
    // TODO: When an error happens, subscribe to extra observable - httpBinApi.backupGet()
  }

  @Test
  public void retry_retryOnError() {
    // TODO: httpBinApi.flakeyGet is a bit flakey and often fails, use retry to make it always complete
  }

  static ResponseBody syntheticBody() {
    return ResponseBody.create(MediaType.get("application/json"), "{}");
  }
}
