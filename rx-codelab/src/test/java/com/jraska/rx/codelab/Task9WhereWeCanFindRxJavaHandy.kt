package com.jraska.rx.codelab

import com.jraska.rx.codelab.http.HttpModule
import com.jraska.rx.codelab.http.RequestInfoCache
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.junit.After
import org.junit.Test

class Task9WhereWeCanFindRxJavaHandy {
  private val httpBinApi = HttpModule.httpBinApi()

  @Test
  fun repeatWhen_refreshFunctionality() {
    val refreshSignal = PublishSubject.create<Any>()

    val request = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())

    // TODO: Perform only one request for both following subscribes - share()
    request.subscribe()
    request.subscribe()

    HttpModule.awaitNetworkRequests()

    // TODO: Make this subscribe receive cached value = cache()
    request.subscribe()

    // TODO: Now refresh the existing observable - use repeatWhen(subject) before calling cache()
    refreshSignal.onNext(Any())
  }

  @Test
  fun repeat_pollingNetwork() {
    val request = httpBinApi.getRequest()
    val endOfPolling = System.currentTimeMillis() + 1000

    // TODO: Implement polling on the request for one second - repeat(), takeUntil()
    // TODO: Add 100ms delay between requests - repeatWhen(), delay()
  }

  @Test
  fun ambWith_effectiveCache() {
    val request = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())
      .share()

    val requestWithCache = RequestInfoCache.requestInfo.mergeWith(request)

    // TODO: requestWithCache has a bug! In rare case when the network request happens to be faster than the cache, ...
    // TODO: ...the last emission will be cached value. Fix this with ambWith

    requestWithCache.subscribe { println(it) }
  }

  @After
  fun after() {
    Thread.sleep(1000)
    HttpModule.awaitNetworkRequests()
  }
}
