package com.jraska.rx.codelab;

import com.jraska.rx.codelab.nature.Earth;
import com.jraska.rx.codelab.nature.Universe;
import com.jraska.rx.codelab.nature.Water;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
  public void test() {
    earth.amazonRiver()
//      .sample(20, TimeUnit.MILLISECONDS)
      .buffer(4)
      .onBackpressureDrop(water -> System.out.println("On Drop " + water))
      .observeOn(Schedulers.newThread())
      .subscribe(batchConsumer());

    sleep(10_000);
  }

  Consumer<Water> slowConsumer() {
    return water -> {
      sleep(18);
      System.out.println(water);
    };
  }

  Consumer<List<Water>> batchConsumer() {
    return water -> {
      sleep(40);
      System.out.println(water);
    };
  }
}
