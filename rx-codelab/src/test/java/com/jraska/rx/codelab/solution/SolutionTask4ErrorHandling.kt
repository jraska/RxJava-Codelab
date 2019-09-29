package com.jraska.rx.codelab.solution

import com.jraska.rx.codelab.RxLogging
import com.jraska.rx.codelab.http.HttpModule
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test

class SolutionTask4ErrorHandling {
  private val httpBinApi = HttpModule.httpBinApi()

  @Before
  fun before() {
    RxLogging.enableObservableSubscribeLogging()
  }

  @Test
  fun printErrorMessage() {
    httpBinApi.failingGet()
      .subscribe(System.out::println, System.err::println)
  }

  @Test
  fun onErrorReturnItem_emitCustomItemOnError() {
    httpBinApi.failingGet()
      .onErrorReturnItem(syntheticBody())
      .subscribe { println(it) }
  }

  @Test
  fun onErrorResumeNext_subscribeToExtraObservableOnError() {
    httpBinApi.failingGet()
      .onErrorResumeNext(httpBinApi.backupGet())
      .subscribe(Consumer { println(it) }, Functions.emptyConsumer())
  }

  @Test
  fun retry_retryOnError() {
    httpBinApi.flakyGet()
      .retry()
      .subscribe { println(it) }
  }

  companion object {
    fun syntheticBody(): ResponseBody {
      return ResponseBody.create(MediaType.get("application/json"), "{}")
    }
  }
}
