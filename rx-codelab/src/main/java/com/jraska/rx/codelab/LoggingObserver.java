package com.jraska.rx.codelab;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static java.lang.System.out;

public class LoggingObserver<T> implements Observer<T> {
    private final String tag;

    public LoggingObserver() {
        this("");
    }

    public LoggingObserver(String tag) {
        this.tag = tag;
    }

    @Override
    public void onSubscribe(Disposable d) {
        out.println(String.format("%s: onSubscribe(%s)", tag, d));
    }

    @Override
    public void onNext(T next) {
        out.println(String.format("%s: onNext(%s)", tag, next));
    }

    @Override
    public void onError(Throwable e) {
        out.println(String.format("%s: onError(%s)", tag, e));
    }

    @Override
    public void onComplete() {
        out.println(String.format("%s: onCompleted", tag));
    }

    public static <T> ObservableTransformer<T, T> transformer(){
        return LoggingObserver::append;
    }

    public static <T> Observable<T> append(Observable<T> observable) {
        LoggingObserver<Object> loggingObserver = new LoggingObserver<>();
        return observable.doOnSubscribe(loggingObserver::onSubscribe)
                .doOnNext(loggingObserver::onNext)
                .doOnError(loggingObserver::onError)
                .doOnComplete(loggingObserver::onComplete);

    }
}
