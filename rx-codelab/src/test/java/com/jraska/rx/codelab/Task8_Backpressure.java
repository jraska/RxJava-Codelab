package com.jraska.rx.codelab;

import com.jraska.rx.codelab.nature.Earth;
import com.jraska.rx.codelab.nature.Universe;
import com.jraska.rx.codelab.nature.Water;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.functions.Consumer;

import static com.jraska.rx.codelab.Utils.sleep;

public class Task8_Backpressure {
  Earth theEarth;

  @Before
  public void before() {
    theEarth = Universe.bigBang().planetEarth();
  }

  @Test
  public void backpressureFail() {
    // TODO: Make subscription to amazonRiver fail on backpressure exception (observeOn needed), use reallySlowConsumer
  }

  @Test
  public void noBackpressure() {
    // TODO: Modify example above to ignore backpressure and continue forever (toObservable())
  }

  @Test
  public void onBackpressureDrop() {
    // TODO: Drop values on backpressure with logging which values are dropped (onBackpressureDrop), use slowConsumer
  }

  @Test
  public void backpressureSample() {
    // TODO: Avoid backpressure errors by sampling the stream by consumer processing time
  }

  @Test
  public void backpressureBatching() {
    // TODO: batch values and process them with batchConsumer()
    // TODO: Experiment with different sizes of buffer
  }

  @Test
  public void onBackpressureBuffer() {
    // TODO: Try different sizes of backpressure buffer to better understand how internal buffers work
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
