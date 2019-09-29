package com.jraska.rx.codelab

import org.junit.After
import org.junit.Test

class Task6SingleCompletableMaybe {

  @Test
  fun helloSingle() {
    // TODO: Create Single emitting one item and subscribe to it printing onSuccess value,
    // TODO: Convert Single to completable and print message about completion.
  }

  @Test
  fun maybe() {
    // TODO: Create a Single with one value to emit and convert it to maybe
  }

  @Test
  fun transformObservableToCompletable() {
    // TODO: Create Observable emitting values 1 .. 10 and make it completable (ignoreElements), subscribe and print
  }

  @Test
  fun intervalRange_firstOrError_observableToSingle() {
    // TODO: Create Observable emitting 5 items each 10 ms (intervalRange)
    // TODO: Get first element (firstOrError)
    // TODO: Play around with skip operator, implement error handling for skip(5)
  }

  @After
  fun after() {
    // to see easily time dependent operations, because we are in unit tests
    Thread.sleep(100)
  }
}
