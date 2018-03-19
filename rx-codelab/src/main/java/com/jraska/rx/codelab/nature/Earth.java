package com.jraska.rx.codelab.nature;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public final class Earth {
  private final River amazonRiver;
  private final River thamesRiver;

  Earth(River amazonRiver, River thamesRiver) {
    this.amazonRiver = amazonRiver;
    this.thamesRiver = thamesRiver;
  }

  public Flowable<Water> amazonRiver() {
    return amazonRiver.flow();
  }

  public Observable<Water> thamesRiver() {
    return thamesRiver.flow().toObservable();
  }

  public Observable<Barrel> oilWell() {
    return Observable.range(1, 10).map(Barrel::new);
  }
}
