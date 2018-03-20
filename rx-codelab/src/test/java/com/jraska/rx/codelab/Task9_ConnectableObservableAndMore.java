package com.jraska.rx.codelab;

import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.schedulers.Schedulers;

import static com.jraska.rx.codelab.Utils.sleep;

public class Task9_ConnectableObservableAndMore {
  HttpBinApi httpBinApi;

  @Before
  public void before() {
    httpBinApi = HttpModule.httpBinApi();
  }

  @Test
  public void publish_refCount_singleRequest() {
    // TODO: execute httpbin get request on io scheduler, subscribe twice and make sure the request happens just once - publish().refCount()
  }

  @Test
  public void replayProcessor_replayValues() {
    // TODO: Create ReplayProcessor<String>
    // TODO: Subscribe with logging into console
    // TODO: Push two strings by onNext()
    // TODO: Subscribe again logging into console
  }

  @Test
  public void behaviorProcessor_storeResponse() {
    // TODO: BehaviorProcessor<RequestInfo> to store and publish value of httpbin request
    // TODO: After some time after request, subscribe again to the processor
  }

  @Test
  public void twoRequestsInParallel_modifiedWithPlugins() {
    // TODO: Make this two parallel requests runs in serial order - RxJavaPlugins.setIoSchedulerHandler

    httpBinApi.getRequest().subscribeOn(Schedulers.io()).subscribe(System.out::println);
    httpBinApi.getRequest().subscribeOn(Schedulers.io()).subscribe(System.out::println);
  }

  @After
  public void after() {
    sleep(2000);
  }
}
