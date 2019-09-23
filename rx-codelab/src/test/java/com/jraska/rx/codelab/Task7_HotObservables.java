package com.jraska.rx.codelab;

import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.RequestInfo;
import com.jraska.rx.codelab.server.Log;
import com.jraska.rx.codelab.server.RxServer;
import com.jraska.rx.codelab.server.RxServerFactory;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static com.jraska.rx.codelab.Utils.sleep;

public class Task7_HotObservables {

  private RxServer rxServer;
  private HttpBinApi httpBinApi;

  @Before
  public void before() {
    rxServer = RxServerFactory.create();
    httpBinApi = HttpModule.httpBinApi();

    RxLogging.enableObservableSubscribeLogging();
  }

  @Test
  public void coldObservable() {
    Observable<RequestInfo> getRequest = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io());

    // TODO: Subscribe twice to getRequest and print its values, how many http requests it triggers?
    // TODO: Delay first subscription by 250 ms - delaySubscription()
    // TODO: Modify getRequest to be able to perform only one http request - share()
  }

  @Test
  public void hotObservable() {
    // TODO: Subscribe twice to rxServer.debugLogsHot and print the logs
    // TODO: Delay first subscription by 250ms - delaySubscription(), how is this different than cold observable
  }

  @Test
  public void createHotObservableThroughSubject() {
    // TODO: Create a PublishSubject<RequestInfo> and subscribe twice to it with printing the result
    // TODO: do HTTP GET request to httpbin.org and publish its values to subject
  }

  @After
  public void after() {
    sleep(500);
    HttpModule.awaitNetworkRequests();
  }
}
