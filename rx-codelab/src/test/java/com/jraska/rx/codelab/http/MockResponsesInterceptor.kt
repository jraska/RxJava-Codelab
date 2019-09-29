package com.jraska.rx.codelab.http

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

/**
 * We have to do this for the code lab to not exceed rate limit from one ip
 */
internal class MockResponsesInterceptor : Interceptor {

  private val random = Random()
  private val mockedResponses = MockedResponses()

  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()

    val response = mockedResponses.get(request)
    if (response != null) {
      sleepToSimulateLatency()
      return response
    } else {
      return chain.proceed(request)
    }
  }

  private fun sleepToSimulateLatency() {
    Thread.sleep((200 + random.nextInt(600)).toLong())
  }
}
