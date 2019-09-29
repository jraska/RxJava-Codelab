package com.jraska.rx.codelab

import io.reactivex.plugins.RxJavaPlugins

object RxLogging {

  fun enableObservableSubscribeLogging() {
    RxJavaPlugins.setOnObservableSubscribe { observable, observer ->
      println(observable.javaClass.getName() + ".onSubscribe(" + observer.javaClass.getSimpleName() + ")")
      return@setOnObservableSubscribe observer
    }
  }
}
