package com.jraska.rx.codelab;

import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
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

public class Task11_Testing {
  private final RxServer rxServer = RxServerFactory.create();
  private final HttpBinApi httpBinApi = HttpModule.httpBinApi();

  @Test
  public void testObserver_onColdObservable() {
    Observable<RequestInfo> request = httpBinApi.getRequest();

    // TODO: Subscribe with test() method to request and assert values count, value has "show_env" in url and no errors were thrown
  }

  @Test
  public void testSubscriber_onHotFlowable() {
    Observable<Log> logObservable = rxServer.debugLogsHot();

    // TODO: Subscribe with test() method to rxServer.debugLogsHot, wait for 5 values(awaitCount), assert no errors and stream not completed
  }

  @Test
  public void testScheduler_advancingTime() {
    TestScheduler testScheduler = new TestScheduler();

    PublishSubject<String> subject = PublishSubject.create();
    Observable<List<String>> bufferedObservable = subject.buffer(100, TimeUnit.MILLISECONDS, testScheduler);
    bufferedObservable.subscribe(System.out::println);

    subject.onNext("First");
    subject.onNext("Batch");
    subject.onNext("Second");
    subject.onNext("Longer");
    subject.onNext("Batch");

    // TODO: Move time of test scheduler so the [First, Batch] and [Second, Longer, Batch] are printed together
  }

  @Test
  public void schedulerProvider_runSynchronouslyInTest() {
    // TODO: Create an instance of IpViewModel and get ip synchronously. Use SchedulerProvider.testSchedulers()
  }
}
