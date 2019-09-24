package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.SchedulerProvider;
import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.IpViewModel;
import com.jraska.rx.codelab.http.RequestInfo;
import com.jraska.rx.codelab.server.Log;
import com.jraska.rx.codelab.server.RxServer;
import com.jraska.rx.codelab.server.RxServerFactory;
import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subjects.PublishSubject;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Solution_Task11_Testing {
  private final RxServer rxServer = RxServerFactory.create();
  private final HttpBinApi httpBinApi = HttpModule.httpBinApi();

  @Test
  public void testObserver_onColdObservable() {
    Observable<RequestInfo> request = httpBinApi.getRequest();
    request.test()
      .assertSubscribed()
      .assertValueCount(1)
      .assertValue(requestInfo -> requestInfo.url.contains("show_env"))
      .assertComplete();
  }

  @Test
  public void testSubscriber_onHotFlowable() {
    Observable<Log> logObservable = rxServer.debugLogsHot();

    logObservable.test()
      .awaitCount(5)
      .assertNotComplete()
      .assertNotTerminated()
      .assertNoErrors();
  }

  @Test
  public void testScheduler_advancingTime() {
    TestScheduler testScheduler = new TestScheduler();

    PublishSubject<String> subject = PublishSubject.create();
    Observable<List<String>> bufferedObservable = subject.buffer(100, TimeUnit.MILLISECONDS, testScheduler);
    bufferedObservable.subscribe(System.out::println);

    subject.onNext("First");
    subject.onNext("Batch");

    testScheduler.advanceTimeBy(100, TimeUnit.MILLISECONDS);

    subject.onNext("Second");
    subject.onNext("Longer");
    subject.onNext("Batch");

    testScheduler.advanceTimeBy(100, TimeUnit.MILLISECONDS);
  }

  @Test
  public void schedulerProvider_runSynchronouslyInTest() {
    IpViewModel viewModel = new IpViewModel(httpBinApi, SchedulerProvider.testSchedulers());

    viewModel.ip().subscribe(System.out::println);
  }
}
