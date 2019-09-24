package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.RequestInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.processors.ReplayProcessor;
import io.reactivex.schedulers.Schedulers;

import static com.jraska.rx.codelab.Utils.sleep;

public class Solution_Task9_WhereWeCanFindRxJavaHandy {
  HttpBinApi httpBinApi;

  @Before
  public void before() {
    httpBinApi = HttpModule.httpBinApi();
  }

  @Test
  public void publish_refCount_singleRequest() {
    Observable<RequestInfo> request = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())
      .publish()
      .refCount();

    request.subscribe();
    request.subscribe();
  }

  @Test
  public void replayProcessor_replayValues() {
    ReplayProcessor<String> replayProcessor = ReplayProcessor.create();

    replayProcessor.subscribe(System.out::println);

    replayProcessor.onNext("Hello");
    replayProcessor.onNext("Replay");

    replayProcessor.subscribe(System.out::println);
  }

  @Test
  public void behaviorProcessor_storeResponse() {
    BehaviorProcessor<RequestInfo> publishProcessor = BehaviorProcessor.create();

    Observable<RequestInfo> request = httpBinApi.getRequest().subscribeOn(Schedulers.io());
    publishProcessor.subscribe(System.out::println);

    request.subscribe(publishProcessor::onNext, publishProcessor::onError);

    sleep(2000);

    publishProcessor.subscribe(System.out::println);
  }

  @Test
  public void twoRequestsInParallel_modifiedWithPlugins() {
    RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.single());

    httpBinApi.getRequest().subscribeOn(Schedulers.io()).subscribe(System.out::println);
    httpBinApi.getRequest().subscribeOn(Schedulers.io()).subscribe(System.out::println);
  }

  @After
  public void after() {
    sleep(2000);
    RxJavaPlugins.reset();
  }
}
