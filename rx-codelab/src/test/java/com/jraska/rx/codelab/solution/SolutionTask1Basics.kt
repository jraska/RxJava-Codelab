package com.jraska.rx.codelab.solution;

import org.junit.Test;

import io.reactivex.Observable;

public class Solution_Task1_Basics {
  @Test
  public void dummyObservable() {
    Observable<String> stringObservable = Observable.just("Hello Rx");

    stringObservable.subscribe(System.out::println);
  }

  @Test
  public void methodIntoObservable() {
    Observable<Long> currentTimeObservable = Observable.fromCallable(System::currentTimeMillis);

    currentTimeObservable.subscribe(System.out::println);
  }

  @Test
  public void helloOperator() {
    Observable<Integer> rangeObservable = Observable.range(1, 10);

    rangeObservable.filter(Solution_Task1_Basics::isOdd)
      .subscribe(System.out::println);
  }

  @Test
  public void receivingError() {
    Observable<Integer> integerObservable = Observable.error(new RuntimeException("I want to crash you"));

    integerObservable.subscribe(System.out::println, System.err::println);
  }

  static boolean isOdd(int value) {
    return value % 2 == 1;
  }
}
