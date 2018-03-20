package com.jraska.rx.codelab;

import org.junit.After;
import org.junit.Test;

import static com.jraska.rx.codelab.Utils.sleep;

public class Task6_SingleCompletableMaybe {

  @Test
  public void helloSingle() {
    // TODO: Create Single emitting one item and subscribe to it printing onSuccess value,
    // TODO: Convert Single to completable and print message about completion.
  }

  @Test
  public void maybe() {
    // TODO: Create a Single with one value to emit and convert it to maybe
  }

  @Test
  public void transformObservableToCompletable() {
    // TODO: Create Observable emitting values 1 .. 10 and make it completable (ignoreElements), subscribe and print
  }

  @Test
  public void intervalRange_firstOrError_observableToSingle() {
    // TODO: Create Observable emitting 5 items each 10 ms (intervalRange)
    // TODO: Get first element (firstOrError)
    // TODO: Play around with skip operator, implement error handling for skip(5)
  }

  @After
  public void after() {
    // to see easily time dependent operations, because we are in unit tests
    sleep(100);
  }
}
