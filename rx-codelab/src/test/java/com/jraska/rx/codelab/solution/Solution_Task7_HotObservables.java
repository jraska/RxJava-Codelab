package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.RequestInfo;
import com.jraska.rx.codelab.nature.Earth;
import com.jraska.rx.codelab.nature.Universe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

import static com.jraska.rx.codelab.Utils.sleep;

public class Solution_Task7_HotObservables {

  Earth theEarth;
  HttpBinApi httpBinApi;

  @Before
  public void before() {
    theEarth = Universe.bigBang().planetEarth();
    httpBinApi = HttpModule.httpBinApi();

    RxJavaPlugins.setOnObservableSubscribe((observable, observer) -> {
      System.out.println("OnSubscribe" + observable.getClass().getSimpleName());

      return observer;
    });
  }

  @Test
  public void coldObservable() {
    theEarth.oilWell().subscribe(System.out::println);
    theEarth.oilWell().subscribe(System.out::println);
  }

  @Test
  public void hotObservable() {
    theEarth.thamesRiver().subscribe(System.out::println);
    theEarth.thamesRiver().subscribe(System.out::println);
  }

  @Test
  public void createHotObservableThroughProcessor() {
    PublishProcessor<RequestInfo> publishProcessor = PublishProcessor.create();

    publishProcessor.subscribe(System.out::println);
    publishProcessor.subscribe(System.out::println);
    publishProcessor.subscribe(System.out::println);

    Observable<RequestInfo> request = httpBinApi.getRequest().subscribeOn(Schedulers.io());

    request.subscribe(publishProcessor::onNext, publishProcessor::onError);
  }

  @After
  public void after() {
    sleep(500);
  }
}
