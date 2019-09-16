package com.jraska.rx.codelab;

import io.reactivex.plugins.RxJavaPlugins;

public class RxLogging {

  public static void enableObservableSubscribeLogging() {
    RxJavaPlugins.setOnObservableSubscribe((observable, observer) -> {
      System.out.println(observable.getClass().getName() + ".onSubscribe(" + observer.getClass().getSimpleName() + ")");
      return observer;
    });
  }
}
