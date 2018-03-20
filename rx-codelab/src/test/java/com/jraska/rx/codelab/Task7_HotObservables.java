package com.jraska.rx.codelab;

import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.nature.Earth;
import com.jraska.rx.codelab.nature.Universe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.jraska.rx.codelab.Utils.sleep;

public class Task7_HotObservables {

  Earth theEarth;
  HttpBinApi httpBinApi;

  @Before
  public void before() {
    theEarth = Universe.bigBang().planetEarth();
    httpBinApi = HttpModule.httpBinApi();

    // TODO: Use RxJavaPlugins to hook into OnObservableSubscribe and print out the sobservable class name when triggered
  }

  @Test
  public void coldObservable() {
    // TODO: Subscribe twice to oilWell from the Earth and print its values
    // TODO: It is important to understand why the ids of barrels do not match
    // TODO: Delay both subscriptions by 250 ms and check what happens
    // TODO: Lets discuss the subscription logs
  }

  @Test
  public void hotObservable() {
    // TODO: Subscribe twice to thamesRiver from the Earth and print its values
    // TODO: It is important to understand why the ids of water do match
    // TODO: Delay both subscriptions by 250 ms and check what happens
    // TODO: Lets discuss the subscription logs
  }

  @Test
  public void createHotObservableThroughProcessor() {
    // TODO: Create a PublishSubject<RequestInfo> and subscribe three times to it with printing the result
    // TODO: do HTTP GET request to httpbin.org and publish its values to subject
  }

  @After
  public void after() {
    sleep(500);
  }
}
