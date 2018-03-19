package com.jraska.rx.codelab.solution;

import org.junit.After;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

import static com.jraska.rx.codelab.Utils.sleep;

public class Solution_Task6_SingleCompletableMaybe {

  @Test
  public void helloSingle() {
    Single<String> single = Single.just("Hello RxJava again");

    single.subscribe(System.out::println);
    Completable completable = single.toCompletable();

    completable.subscribe(() -> System.out.println("Completed"));
  }

  @Test
  public void maybe() {
    Maybe<String> maybe = Single.just("Hello RxJava again").toMaybe();

    maybe.subscribe(System.out::println, System.err::println);
  }

  @Test
  public void transformObservableToCompletable() {
    Observable<Integer> range = Observable.range(0, 10);

    Completable completable = range.ignoreElements();

    completable.subscribe(System.out::println);
  }

  @Test
  public void intervalRange_firstOrError_observableToSingle() {
    Observable<Long> range = Observable.intervalRange(0, 5, 0, 10, TimeUnit.MILLISECONDS);

    range.skip(4).firstOrError().subscribe(System.out::println);
  }

  @After
  public void after() {
    // to see easily time dependent operations, because we are in unit tests
    sleep(100);
  }
}
