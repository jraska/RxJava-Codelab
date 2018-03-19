package com.jraska.rx.codelab;

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

public class Task8_Backpressure {
  Earth earth;

  @Before
  public void before() {
    earth = Universe.bigBang().planetEarth();
  }

  @Test
  public void noBackpressure() {
    earth.amazonRiver()
      .toObservable()
      .observeOn(Schedulers.newThread())
      .subscribe(reallySlowConsumer());
  }

  @Test
  public void backpressureFail() {
    earth.amazonRiver()
      .observeOn(Schedulers.newThread())
      .subscribe(reallySlowConsumer());
  }

  @Test
  public void onBackpressureDrop() {
    earth.amazonRiver()
      .onBackpressureDrop(water -> System.out.println("On Drop " + water))
      .observeOn(Schedulers.newThread())
      .subscribe(slowConsumer());
  }

  @Test
  public void backpressureSample() {
    earth.amazonRiver()
      .sample(25, TimeUnit.MILLISECONDS)
      .observeOn(Schedulers.newThread())
      .subscribe(slowConsumer());
  }

  @Test
  public void backpressureBatching() {
    earth.amazonRiver()
      .buffer(2)
      .observeOn(Schedulers.newThread())
      .subscribe(batchConsumer());
  }

  @Test
  public void onBackpressureBuffer() {
    earth.amazonRiver()
      .onBackpressureBuffer(10)
      .observeOn(Schedulers.newThread())
      .subscribe(slowConsumer());
  }

  @Test
  public void onBackpressureLatest() {
    earth.amazonRiver()
      .onBackpressureLatest()
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
