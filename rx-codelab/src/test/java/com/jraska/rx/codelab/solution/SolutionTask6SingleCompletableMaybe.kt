package com.jraska.rx.codelab.solution

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Consumer
import org.junit.After
import org.junit.Test
import java.util.concurrent.TimeUnit

class SolutionTask6SingleCompletableMaybe {

  @Test
  fun helloSingle() {
    val single = Single.just("Hello RxJava again")

    single.subscribe(Consumer { println(it) })
    val completable = single.ignoreElement()

    completable.subscribe { println("Completed") }
  }

  @Test
  fun maybe() {
    val maybe = Single.just("Hello RxJava again").toMaybe()

    maybe.subscribe(System.out::println, System.err::println)
  }

  @Test
  fun transformObservableToCompletable() {
    val range = Observable.range(0, 10)

    val completable = range.ignoreElements()

    completable.subscribe { println("Completed") }
  }

  @Test
  fun intervalRange_firstOrError_observableToSingle() {
    val range = Observable.intervalRange(0, 5, 0, 10, TimeUnit.MILLISECONDS)

    range.skip(4).firstOrError().subscribe(Consumer { println(it) })
  }

  @After
  fun after() {
    // to see easily time dependent operations, because we are in unit tests
    Thread.sleep(100)
  }
}
