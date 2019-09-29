package com.jraska.rx.codelab.server

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.processors.PublishProcessor
import java.util.concurrent.TimeUnit

internal class ChattyServer : RxServer {
  private val logs = PublishProcessor.create<Log>()

  override fun allLogsHot(): Flowable<Log> {
    return logs
  }

  override fun debugLogsHot(): Observable<Log> {
    return logs.filter { log -> log.level.value >= Log.Level.DEBUG.value }.toObservable()
  }

  companion object {

    fun create(): ChattyServer {
      val chattyServer = ChattyServer()
      Observable.interval(5, TimeUnit.MILLISECONDS)
        .map { Log(Log.Level.VERBOSE, "I'm too chatty") }
        .subscribe { chattyServer.logs.onNext(it) }

      Observable.interval(100, TimeUnit.MILLISECONDS)
        .map { Log(Log.Level.DEBUG, "I'm happily running") }
        .subscribe { chattyServer.logs.onNext(it) }

      return chattyServer
    }
  }
}
