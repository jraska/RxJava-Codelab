package com.jraska.rx.codelab

import com.jraska.rx.codelab.http.HttpBinApi
import com.jraska.rx.codelab.http.HttpModule
import com.jraska.rx.codelab.server.RxServer
import com.jraska.rx.codelab.server.RxServerFactory
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

class Task7HotObservables {

  private val rxServer: RxServer = RxServerFactory.create()
  private val httpBinApi: HttpBinApi = HttpModule.httpBinApi()

  @Before
  fun before() {
    RxLogging.enableObservableSubscribeLogging()
  }

  @Test
  fun coldObservable() {
    val getRequest = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())

    // TODO: Subscribe twice to getRequest and print its values, how many http requests it triggers?
    // TODO: Delay first subscription by 250 ms - delaySubscription()
    // TODO: Modify getRequest to be able to perform only one http request - share()
  }

  @Test
  fun hotObservable() {
    // TODO: Subscribe twice to rxServer.debugLogsHot and print the logs
    // TODO: Delay first subscription by 250ms - delaySubscription(), how is this different than cold observable
  }

  @Test
  fun createHotObservableThroughSubject() {
    val getRequest = httpBinApi.getRequest()

    // TODO: Create a PublishSubject<RequestInfo> and subscribe twice to it with printing the result
    // TODO: Subscribe to getRequest and publish its values to subject
  }

  @After
  fun after() {
    Thread.sleep(500)
    HttpModule.awaitNetworkRequests()
  }
}
