package com.jraska.rx.codelab;

import com.jraska.rx.codelab.server.Log;
import com.jraska.rx.codelab.server.RxServer;
import com.jraska.rx.codelab.server.RxServerFactory;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.jraska.rx.codelab.Utils.sleep;

public class Task10_Backpressure {
  private RxServer rxServer;

  @Before
  public void before() {
    rxServer = RxServerFactory.create();
  }

  @Test
  public void backpressureFail() {
    // TODO: Subscribe to rxServer.allLogsHot on different thread (observeOn), use reallySlowLogConsumer
  }

  @Test
  public void noBackpressure() {
    // TODO: Modify example above to ignore backpressure and continue forever (toObservable())
  }

  @Test
  public void onBackpressureDrop() {
    // TODO: Drop values on backpressure with logging which values are dropped (onBackpressureDrop), use slowLogConsumer
  }

  @Test
  public void buffer_backpressureBatching() {
    // TODO: batch values and process them with batchLogsConsumer()
    // TODO: Experiment with different sizes of buffer
  }

  @Test
  public void onBackpressureBuffer() {
    // TODO: Try different sizes of backpressure buffer to better understand how internal buffers work
  }

  private Consumer<Log> slowLogConsumer() {
    return log -> {
      sleep(25);
      System.out.println(log);
    };
  }

  private Consumer<Log> reallySlowLogConsumer() {
    return log -> {
      sleep(100);
      System.out.println(log);
    };
  }

  private Consumer<List<Log>> batchLogsConsumer() {
    return logs -> {
      sleep(100);
      System.out.println(logs);
    };
  }

  @After
  public void after() {
    sleep(3_000);
  }
}
