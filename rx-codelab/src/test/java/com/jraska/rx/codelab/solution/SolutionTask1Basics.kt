package com.jraska.rx.codelab.solution

import io.reactivex.Observable
import org.junit.Test

class SolutionTask1Basics {
  @Test
  fun dummyObservable() {
    val stringObservable = Observable.just("Hello Rx")

    stringObservable.subscribe { println(it) }
  }

  @Test
  fun methodIntoObservable() {
    val currentTimeObservable = Observable.fromCallable<Long> { System.currentTimeMillis() }

    currentTimeObservable.subscribe { println(it) }
  }

  @Test
  fun helloOperator() {
    val rangeObservable = Observable.range(1, 10)

    rangeObservable.filter { isOdd(it) }
      .subscribe(System.out::println)
  }

  @Test
  fun receivingError() {
    val integerObservable = Observable.error<Int>(RuntimeException("I want to crash you"))

    integerObservable.subscribe(::println, System.err::println)
  }

  companion object {
    fun isOdd(value: Int): Boolean {
      return value % 2 == 1
    }
  }
}
