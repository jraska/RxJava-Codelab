package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.Utils;
import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.RequestInfo;

import com.jraska.rx.codelab.http.RequestInfoCache;
import io.reactivex.subjects.PublishSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.processors.ReplayProcessor;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

import static com.jraska.rx.codelab.Utils.sleep;

public class Solution_Task9_WhereWeCanFindRxJavaHandy {
  private final HttpBinApi httpBinApi = HttpModule.httpBinApi();

  @Test
  public void repeatWhen_refreshFunctionality() {
    PublishSubject<Object> refreshSignal = PublishSubject.create();

    Observable<RequestInfo> request = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())
      .share()
      .repeatWhen(any -> refreshSignal)
      .cache();

    request.subscribe();
    request.subscribe();

    HttpModule.awaitNetworkRequests();

    request.subscribe();

    refreshSignal.onNext(new Object());
  }

  @Test
  public void repeat_pollingNetwork() {
    Observable<RequestInfo> request = httpBinApi.getRequest();
    long endOfPolling = System.currentTimeMillis() + 1000;

    request.repeatWhen(observable -> observable.delay(100, TimeUnit.MILLISECONDS))
      .takeUntil(info -> System.currentTimeMillis() > endOfPolling)
      .subscribe();
  }

  @Test
  public void ambWith_effectiveCache() {
    Observable<RequestInfo> request = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())
      .share();

    Observable<RequestInfo> requestWithCache = RequestInfoCache.getRequestInfo().mergeWith(request);

    Observable<RequestInfo> observableWithCache = request.ambWith(requestWithCache);
    observableWithCache.subscribe(System.out::println);
  }

  @After
  public void after() {
    Utils.sleep(1000);
    HttpModule.awaitNetworkRequests();
  }
}
