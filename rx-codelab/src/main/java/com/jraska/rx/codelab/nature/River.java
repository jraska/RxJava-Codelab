package com.jraska.rx.codelab.nature;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.schedulers.Schedulers;

public final class River {
  private final Flowable<Water> coldWaterFlowable;
  private ConnectableFlowable<Water> stream;

  River(long period) {
    coldWaterFlowable = Flowable.interval(period, TimeUnit.MILLISECONDS, Schedulers.computation())
      .map(Water::new)
      .timeout(5, TimeUnit.MINUTES); // in case someone forgets tests running
  }

  void startFlow() {
    stream = coldWaterFlowable.publish();
    stream.connect();
  }

  Flowable<Water> flow() {
    return stream;
  }
}
