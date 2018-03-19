package com.jraska.rx.codelab;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.disposables.Disposable;

import static java.lang.System.out;

public class LoggingObserver<T> {
  private final String tag;

  public LoggingObserver() {
    this("");
  }

  public LoggingObserver(String tag) {
    this.tag = tag;
  }

  void onSubscribe(Disposable d) {
    out.println(String.format("%s: onSubscribe(%s)", tag, d));
  }

  void onNext(T next) {
    out.println(String.format("%s: onNext(%s)", tag, next));
  }

  void onSuccess(T next) {
    out.println(String.format("%s: onSuccess(%s)", tag, next));
  }

  void onError(Throwable e) {
    out.println(String.format("%s: onError(%s)", tag, e));
  }

  void onComplete() {
    out.println(String.format("%s: onCompleted", tag));
  }

  public static <T> ObservableTransformer<T, T> transformer() {
    return LoggingObserver::append;
  }

  public static <T> SingleTransformer<T, T> singleTransformer() {
    return LoggingObserver::append;
  }

  public static <T> Single<T> append(Single<T> observable) {
    LoggingObserver<Object> loggingObserver = new LoggingObserver<>();
    return observable.doOnSubscribe(loggingObserver::onSubscribe)
      .doOnSuccess(loggingObserver::onSuccess)
      .doOnError(loggingObserver::onError);
  }

  public static <T> Observable<T> append(Observable<T> observable) {
    LoggingObserver<Object> loggingObserver = new LoggingObserver<>();
    return observable.doOnSubscribe(loggingObserver::onSubscribe)
      .doOnNext(loggingObserver::onNext)
      .doOnError(loggingObserver::onError)
      .doOnComplete(loggingObserver::onComplete);

  }
}
