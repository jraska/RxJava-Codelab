package com.jraska.rx.codelab.server;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.processors.PublishProcessor;

import java.util.concurrent.TimeUnit;

final class ChattyServer implements RxServer {
  private PublishProcessor<Log> logs = PublishProcessor.create();

  @Override
  public Flowable<Log> allLogsHot() {
    return logs;
  }

  @Override
  public Observable<Log> debugLogsHot() {
    return logs.filter(log -> log.level.value >= Log.Level.DEBUG.value).toObservable();
  }

  static ChattyServer create() {
    ChattyServer chattyServer = new ChattyServer();
    Observable.interval(5, TimeUnit.MILLISECONDS)
      .map(aLong -> new Log(Log.Level.VERBOSE, "I'm too chatty"))
      .subscribe(chattyServer.logs::onNext);

    Observable.interval(100, TimeUnit.MILLISECONDS)
      .map(aLong -> new Log(Log.Level.DEBUG, "I'm happily running"))
      .subscribe(chattyServer.logs::onNext);

    return chattyServer;
  }
}
