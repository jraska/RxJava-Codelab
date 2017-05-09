package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.furniture.Parts;
import com.jraska.rx.codelab.furniture.Screw;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;

public class Solution_Task4_ErrorHandling {
  Observable<Screw> screwsObservable;
  Observable<Screw> extraScrewsObservable;

  @Before
  public void before() {
    screwsObservable = Observable.fromIterable(Parts.fiveScrews())
      .concatWith(Observable.error(new RuntimeException("Damaged screw!")));

    extraScrewsObservable = Observable.fromIterable(Parts.fiveScrews());
  }

  @Test
  public void printErrorMessage() {
    screwsObservable.subscribe(System.out::println, throwable -> System.out.println(throwable.getMessage()));
  }

  @Test
  public void emitCustomItemOnError() {
    screwsObservable.onErrorReturnItem(Parts.screw()).subscribe(System.out::println);
  }

  @Test
  public void subscribeToExtraObservableOnError() {
    screwsObservable.onErrorResumeNext(extraScrewsObservable).subscribe(System.out::println);
  }

  @Test
  public void retryOnError() {
    Observable<Screw> flakeyObservable = Parts.flakeyScrew().retry();

    for (int i = 0; i < 10; i++) {
      flakeyObservable.subscribe(System.out::println);
    }
  }
}
