package com.jraska.rx.codelab.solution

import com.jraska.rx.codelab.http.HttpModule
import com.jraska.rx.codelab.http.RequestInfoCache
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.junit.After
import org.junit.Test
import java.util.concurrent.TimeUnit

class SolutionTask9WhereWeCanFindRxJavaHandy {
  private val httpBinApi = HttpModule.httpBinApi()

  @Test
  fun repeatWhen_refreshFunctionality() {
    val refreshSignal = PublishSubject.create<Any>()

    val request = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())
      .share()
      .repeatWhen { refreshSignal }
      .cache()

    request.subscribe()
    request.subscribe()

    HttpModule.awaitNetworkRequests()

    request.subscribe()

    refreshSignal.onNext(Any())
  }

  @Test
  fun repeat_pollingNetwork() {
    val request = httpBinApi.getRequest()
    val endOfPolling = System.currentTimeMillis() + 1000

    request.repeatWhen { observable -> observable.delay(100, TimeUnit.MILLISECONDS) }
      .takeUntil { System.currentTimeMillis() > endOfPolling }
      .subscribe()
  }

  @Test
  fun ambWith_effectiveCache() {
    val request = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())
      .share()

    val requestWithCache = RequestInfoCache.requestInfo.mergeWith(request)

    val observableWithCache = request.ambWith(requestWithCache)
    observableWithCache.subscribe { println(it) }
  }

  @After
  fun after() {
    Thread.sleep(1000)
    HttpModule.awaitNetworkRequests()
  }
}
