package com.jraska.rx.codelab;

import com.jraska.rx.codelab.nature.Earth;
import com.jraska.rx.codelab.nature.Universe;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subjects.PublishSubject;
import org.junit.Before;
import org.junit.Test;

public class Task11_Testing {
  Earth theEarth;

  @Before
  public void before() {
    theEarth = Universe.bigBang().planetEarth();
  }

  @Test
  public void testObserver_onColdObservable() {
    // TODO: Subscribe with test() method to oilWell and use as many assertions as possible on TestObserver
  }

  @Test
  public void testSubscriber_onHotFlowable() {
    // TODO: Subscribe with test() method to amazonRiver, wait for 5 values and perfor all possible assertions after await
  }

  @Test
  public void testScheduler_makeIntervalTickFast() {
    TestScheduler testScheduler = new TestScheduler();

    // TODO: Create interval Observable ticking every 100 ms, use TestScheduler to tick 100 times without Thread.sleep()
  }

  @Test
  public void testScheduler_debounceTesting() {
    PublishSubject<Integer> subject = PublishSubject.create();

    TestScheduler testScheduler = new TestScheduler();

    // TODO: Debounce values by 10ms and subscriber with printing to console
    // TODO: Publish 20 times value to on next, advance time of test scheduler in each iteration
  }
}
