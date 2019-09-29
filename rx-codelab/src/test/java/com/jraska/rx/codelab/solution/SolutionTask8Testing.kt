package com.jraska.rx.codelab.solution

import com.jraska.rx.codelab.SchedulerProvider
import com.jraska.rx.codelab.http.HttpModule
import com.jraska.rx.codelab.http.IpViewModel
import com.jraska.rx.codelab.server.RxServerFactory
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import java.util.concurrent.TimeUnit

class SolutionTask8Testing {
  private val rxServer = RxServerFactory.create()
  private val httpBinApi = HttpModule.httpBinApi()

  @Test
  fun testObserver_onColdObservable() {
    val request = httpBinApi.getRequest()
    request.test()
      .assertSubscribed()
      .assertValueCount(1)
      .assertValue { requestInfo -> requestInfo.url.contains("show_env") }
      .assertComplete()
  }

  @Test
  fun testSubscriber_onHotFlowable() {
    val logObservable = rxServer.debugLogsHot()

    logObservable.test()
      .awaitCount(5)
      .assertNotComplete()
      .assertNotTerminated()
      .assertNoErrors()
  }

  @Test
  fun testScheduler_advancingTime() {
    val testScheduler = TestScheduler()

    val subject = PublishSubject.create<String>()
    val bufferedObservable = subject.buffer(100, TimeUnit.MILLISECONDS, testScheduler)
    bufferedObservable.subscribe { println(it) }

    subject.onNext("First")
    subject.onNext("Batch")

    testScheduler.advanceTimeBy(100, TimeUnit.MILLISECONDS)

    subject.onNext("Second")
    subject.onNext("Longer")
    subject.onNext("Batch")

    testScheduler.advanceTimeBy(100, TimeUnit.MILLISECONDS)
  }

  @Test
  fun schedulerProvider_runSynchronouslyInTest() {
    val viewModel = IpViewModel(httpBinApi, SchedulerProvider.testSchedulers())

    viewModel.ip().subscribe({ println(it) })
  }
}
