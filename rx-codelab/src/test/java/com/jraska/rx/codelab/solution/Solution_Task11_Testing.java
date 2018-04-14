package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.nature.Barrel;
import com.jraska.rx.codelab.nature.Earth;
import com.jraska.rx.codelab.nature.Universe;
import com.jraska.rx.codelab.nature.Water;
import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subjects.PublishSubject;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Solution_Task11_Testing {
  Earth theEarth;

  @Before
  public void before() {
    theEarth = Universe.bigBang().planetEarth();
  }

  @Test
  public void testObserver_onColdObservable() {
    theEarth.oilWell()
      .test()
      .assertSubscribed()
      .assertNoErrors()
      .assertValueCount(10)
      .assertComplete()
      .assertTerminated()
      .assertNever(new Barrel(12345));
  }

  @Test
  public void testSubscriber_onHotFlowable() {
    theEarth.amazonRiver()
      .test()
      .awaitCount(5)
      .assertNoTimeout()
      .assertSubscribed()
      .assertValueCount(5)
      .assertNoErrors()
      .assertNotComplete()
      .assertNotTerminated()
      .assertNever(new Water(12345));
  }

  @Test
  public void testScheduler_makeIntervalTickFast() {
    TestScheduler testScheduler = new TestScheduler();

    Observable<Long> interval = Observable.interval(100, TimeUnit.MILLISECONDS, testScheduler);
    interval.subscribe(System.out::println);

    for (int i = 0; i < 100; i++) {
      testScheduler.advanceTimeBy(100, TimeUnit.MILLISECONDS);
    }
  }

  @Test
  public void testScheduler_debounceTesting() {
    PublishSubject<Integer> subject = PublishSubject.create();

    TestScheduler testScheduler = new TestScheduler();

    subject.debounce(10, TimeUnit.MILLISECONDS, testScheduler)
      .subscribe(System.out::println);

    for (int i = 0; i < 20; i++) {
      subject.onNext(i);
      testScheduler.advanceTimeBy(i, TimeUnit.MILLISECONDS);
    }
  }
}
