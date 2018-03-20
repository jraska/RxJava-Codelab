package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.nature.Earth;
import com.jraska.rx.codelab.nature.Universe;
import com.jraska.rx.codelab.nature.Water;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.jraska.rx.codelab.Utils.sleep;

public class Solution_Task8_Backpressure {
  Earth theEarth;

  @Before
  public void before() {
    theEarth = Universe.bigBang().planetEarth();
  }

  @Test
  public void backpressureFail() {
    theEarth.amazonRiver()
      .observeOn(Schedulers.newThread())
      .subscribe(reallySlowConsumer());
  }

  @Test
  public void noBackpressure() {
    theEarth.amazonRiver()
      .toObservable()
      .observeOn(Schedulers.newThread())
      .subscribe(reallySlowConsumer());
  }

  @Test
  public void onBackpressureDrop() {
    theEarth.amazonRiver()
      .onBackpressureDrop(water -> System.out.println("On Drop " + water))
      .observeOn(Schedulers.newThread())
      .subscribe(slowConsumer());
  }

  @Test
  public void backpressureSample() {
    theEarth.amazonRiver()
      .sample(25, TimeUnit.MILLISECONDS)
      .observeOn(Schedulers.newThread())
      .subscribe(slowConsumer());
  }

  @Test
  public void backpressureBatching() {
    theEarth.amazonRiver()
      .buffer(2)
      .observeOn(Schedulers.newThread())
      .subscribe(batchConsumer());
  }

  @Test
  public void onBackpressureBuffer() {
    theEarth.amazonRiver()
      .onBackpressureBuffer(10)
      .observeOn(Schedulers.newThread())
      .subscribe(slowConsumer());
  }

  Consumer<Water> slowConsumer() {
    return water -> {
      sleep(25);
      System.out.println(water);
    };
  }

  Consumer<Water> reallySlowConsumer() {
    return water -> {
      sleep(100);
      System.out.println(water);
    };
  }

  Consumer<List<Water>> batchConsumer() {
    return water -> {
      sleep(100);
      System.out.println(water);
    };
  }

  @After
  public void after() {
    sleep(5_000);
  }
}
