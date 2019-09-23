package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.RxLogging;
import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.RequestInfo;
import com.jraska.rx.codelab.nature.Earth;
import com.jraska.rx.codelab.nature.Universe;

import com.jraska.rx.codelab.server.Log;
import com.jraska.rx.codelab.server.RxServer;
import com.jraska.rx.codelab.server.RxServerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

import static com.jraska.rx.codelab.Utils.sleep;

public class Solution_Task7_HotObservables {

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
      .subscribeOn(Schedulers.io())
      .share();

    getRequest.subscribe(System.out::println);
    getRequest.subscribe(System.out::println);
  }

  @Test
  public void hotObservable() {
    Observable<Log> logs = rxServer.debugLogsHot();

    logs.delaySubscription(250, TimeUnit.MILLISECONDS).subscribe(System.out::println);
    logs.subscribe(System.out::println);
  }

  @Test
  public void createHotObservableThroughSubject() {
    PublishProcessor<RequestInfo> publishProcessor = PublishProcessor.create();

    publishProcessor.subscribe(System.out::println);
    publishProcessor.subscribe(System.out::println);

    Observable<RequestInfo> request = httpBinApi.getRequest().subscribeOn(Schedulers.io());

    request.subscribe(publishProcessor::onNext, publishProcessor::onError);
  }

  @After
  public void after() {
    sleep(500);
    HttpModule.awaitNetworkRequests();
  }
}
