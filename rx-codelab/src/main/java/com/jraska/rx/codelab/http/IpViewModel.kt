package com.jraska.rx.codelab.http

import com.jraska.rx.codelab.SchedulerProvider
import io.reactivex.Observable

class IpViewModel(private val httpBinApi: HttpBinApi, private val schedulerProvider: SchedulerProvider) {

  fun ip(): Observable<String> {
    return httpBinApi.getRequest()
      .subscribeOn(schedulerProvider.io)
      .observeOn(schedulerProvider.main)
      .map { requestInfo -> requestInfo.origin }
  }
}
