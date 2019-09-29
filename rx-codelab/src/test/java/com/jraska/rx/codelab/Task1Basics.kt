package com.jraska.rx.codelab

import org.junit.Test

class Task1Basics {
  @Test
  fun dummyObservable() {
    // TODO:  Create Observable with single String value, subscribe to it and print it to console (Observable.just)
  }

  @Test
  fun methodIntoObservable() {
    // TODO:  Create Observable getting current time, subscribe to it and print value to console (Observable.fromCallable)
  }

  @Test
  fun helloOperator() {
    // TODO:  Create Observable with ints 1 .. 10 subscribe to it and print only odd values (Observable.range, observable.filter)
  }

  @Test
  fun receivingError() {
    // TODO:  Create Observable which emits an error and print the console (Observable.error), subscribe with onError handling
  }

  companion object {
    fun isOdd(value: Int): Boolean {
      return value % 2 == 1
    }
  }
}
