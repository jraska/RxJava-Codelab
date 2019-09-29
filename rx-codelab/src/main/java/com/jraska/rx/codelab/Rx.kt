package com.jraska.rx.codelab

import io.reactivex.Observable
import io.reactivex.functions.BiFunction

fun <T1, T2, R> Observable<T1>.combineLatest(
  other: Observable<T2>,
  combiner: (T1, T2) -> R
): Observable<R> {
  return Observable.combineLatest(this, other, BiFunction(combiner))
}

fun <T1, T2, R> Observable<T1>.zipWith(
  other: Observable<T2>,
  combiner: (T1, T2) -> R
): Observable<R> {
  return this.zipWith(other, BiFunction(combiner))
}
