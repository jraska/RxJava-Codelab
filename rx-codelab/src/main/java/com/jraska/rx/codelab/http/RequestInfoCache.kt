package com.jraska.rx.codelab.http

import io.reactivex.Observable

import java.util.concurrent.TimeUnit

/**
 * Showcase purpose - not a cache at all :P
 */
object RequestInfoCache {
  val requestInfo: Observable<RequestInfo>
    get() = Observable.fromCallable { requestSync }
      .delay(400, TimeUnit.MILLISECONDS)

  private val requestSync: RequestInfo
    get() {
      val requestInfo = RequestInfo()
      requestInfo.origin = "cache"

      return requestInfo
    }
}
