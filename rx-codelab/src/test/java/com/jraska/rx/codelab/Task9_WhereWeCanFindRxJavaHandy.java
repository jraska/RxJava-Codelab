package com.jraska.rx.codelab;

import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.RequestInfo;
import com.jraska.rx.codelab.http.RequestInfoCache;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import org.junit.After;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Task9_WhereWeCanFindRxJavaHandy {
  private final HttpBinApi httpBinApi = HttpModule.httpBinApi();

  @Test
  public void repeatWhen_refreshFunctionality() {
    PublishSubject<Object> refreshSignal = PublishSubject.create();

    Observable<RequestInfo> request = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io());

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
  public void repeat_pollingNetwork() {
    Observable<RequestInfo> request = httpBinApi.getRequest();
    long endOfPolling = System.currentTimeMillis() + 1000;

    // TODO: Implement polling on the request for one second - repeat(), takeUntil()
    // TODO: Add 100ms delay between requests - repeatWhen(), delay()
  }

  @Test
  public void ambWith_effectiveCache() {
    Observable<RequestInfo> request = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())
      .share();

    Observable<RequestInfo> requestWithCache = RequestInfoCache.getRequestInfo().mergeWith(request);

    // TODO: requestWithCache has a bug! In rare case when the network request happens to be faster than the cache, ...
    // TODO: ...the last emission will be cached value. Fix this with ambWith

    requestWithCache.subscribe(System.out::println);
  }

  @After
  public void after() {
    Utils.sleep(1000);
    HttpModule.awaitNetworkRequests();
  }
}
