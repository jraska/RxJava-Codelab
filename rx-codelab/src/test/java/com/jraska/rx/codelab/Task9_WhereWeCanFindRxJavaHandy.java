package com.jraska.rx.codelab;

import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.RequestInfo;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import org.junit.After;
import org.junit.Test;

public class Task9_WhereWeCanFindRxJavaHandy {
  private final HttpBinApi httpBinApi = HttpModule.httpBinApi();

  @Test
  public void repeatWhen_refreshFunctionality() {
    PublishSubject<Object> refreshSignal = PublishSubject.create();

    Observable<RequestInfo> request = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())
      .share()
      .repeatWhen(any -> refreshSignal)
      .cache();

    // TODO: Perform only one request for both following subscribes - share()
    request.subscribe();
    request.subscribe();

    HttpModule.awaitNetworkRequests();

    // TODO: Make this subscribe receive cached value = cache()
    request.subscribe();

    // TODO: Now refresh the existing observable - use repeatWhen(subject) before calling cache()
    refreshSignal.onNext(new Object());
  }

  @Test
  public void replayProcessor_replayValues() {
    // TODO: Create ReplayProcessor<String>
    // TODO: Subscribe with logging into console
    // TODO: Push two strings by onNext()
    // TODO: Subscribe again logging into console
  }

  @Test
  public void behaviorProcessor_storeResponse() {
    // TODO: BehaviorProcessor<RequestInfo> to store and publish value of httpbin request
    // TODO: After some time after request, subscribe again to the processor
  }

  @Test
  public void twoRequestsInParallel_modifiedWithPlugins() {
    // TODO: Make this two parallel requests runs in serial order - RxJavaPlugins.setIoSchedulerHandler

    httpBinApi.getRequest().subscribeOn(Schedulers.io()).subscribe(System.out::println);
    httpBinApi.getRequest().subscribeOn(Schedulers.io()).subscribe(System.out::println);
  }

  @After
  public void after() {
    HttpModule.awaitNetworkRequests();
  }
}
