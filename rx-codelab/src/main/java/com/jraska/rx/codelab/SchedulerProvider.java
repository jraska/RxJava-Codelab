package com.jraska.rx.codelab;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider {
  private final Scheduler main;
  private final Scheduler io;
  private final Scheduler computation;

  SchedulerProvider(Scheduler main, Scheduler io, Scheduler computation) {
    this.main = main;
    this.io = io;
    this.computation = computation;
  }

  public Scheduler getMain() {
    return main;
  }

  public Scheduler getIo() {
    return io;
  }

  public Scheduler getComputation() {
    return computation;
  }

  public static SchedulerProvider testSchedulers() {
    return new SchedulerProvider(Schedulers.trampoline(), Schedulers.trampoline(), Schedulers.trampoline());
  }
}
