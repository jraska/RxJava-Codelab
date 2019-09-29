package com.jraska.rx.codelab.solution

import com.jraska.rx.codelab.server.Log
import com.jraska.rx.codelab.server.RxServer
import com.jraska.rx.codelab.server.RxServerFactory
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Test

class SolutionTask10Backpressure {
  private val rxServer: RxServer = RxServerFactory.create()

  @Test
  fun backpressureFail() {
    rxServer.allLogsHot()
      .observeOn(Schedulers.newThread())
      .subscribe(reallySlowLogConsumer())
  }

  @Test
  fun noBackpressure() {
    rxServer.allLogsHot()
      .toObservable()
      .observeOn(Schedulers.newThread())
      .subscribe(reallySlowLogConsumer())
  }

  @Test
  fun onBackpressureDrop() {
    rxServer.allLogsHot()
      .onBackpressureDrop { water -> println("On Drop $water") }
      .observeOn(Schedulers.newThread())
      .subscribe(slowLogConsumer())
  }

  @Test
  fun buffer_backpressureBatching() {
    rxServer.allLogsHot()
      .buffer(5)
      .observeOn(Schedulers.newThread())
      .subscribe(batchLogsConsumer())
  }

  @Test
  fun onBackpressureBuffer() {
    rxServer.allLogsHot()
      .onBackpressureBuffer(128)
      .observeOn(Schedulers.newThread())
      .subscribe(slowLogConsumer())
  }

  private fun slowLogConsumer(): Consumer<Log> {
    return Consumer { log ->
      Thread.sleep(25)
      println(log)
    }
  }

  private fun reallySlowLogConsumer(): Consumer<Log> {
    return Consumer { log ->
      Thread.sleep(100)
      println(log)
    }
  }

  private fun batchLogsConsumer(): Consumer<List<Log>> {
    return Consumer { logs ->
      Thread.sleep(100)
      println(logs)
    }
  }

  @After
  fun after() {
    Thread.sleep(3000)
  }
}
