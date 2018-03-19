package com.jraska.rx.codelab;

import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.RequestInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.BiFunction;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

import static com.jraska.rx.codelab.Utils.sleep;

public class Task9_ConnectableObservableAndMore {
  HttpBinApi httpBinApi;

  @Test
  public void publish_refCount_singleRequest() {
    Observable<RequestInfo> request = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())
      .publish()
      .refCount();

    request.subscribe();
    request.subscribe();
  }

  @Test
  public void autoConnect_singleRequest() {
    Observable<RequestInfo> request = httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())
      .publish()
      .autoConnect();

    request.subscribe();
    request.subscribe();
  }

  @Test
  public void publishProcessor_multiCastResponse() {
    PublishProcessor<RequestInfo> publishProcessor = PublishProcessor.create();

    Observable<RequestInfo> request = httpBinApi.getRequest().subscribeOn(Schedulers.io());

    publishProcessor.subscribe(System.out::println);
    publishProcessor.subscribe(System.out::println);
    publishProcessor.subscribe(System.out::println);

    request.subscribe(publishProcessor::onNext, publishProcessor::onError);
  }

  @Test
  public void behaviorProcessor_storeResponse() {
    BehaviorProcessor<RequestInfo> publishProcessor = BehaviorProcessor.create();

    Observable<RequestInfo> request = httpBinApi.getRequest().subscribeOn(Schedulers.io());
    publishProcessor.subscribe(System.out::println);

    request.subscribe(publishProcessor::onNext, publishProcessor::onError);

    sleep(2000);

    publishProcessor.subscribe(System.out::println);
  }

  @Before
  public void before() {
    httpBinApi = HttpModule.httpBinApi();

    RxJavaPlugins.setOnObservableSubscribe(new BiFunction<Observable, Observer, Observer>() {
      @Override
      public Observer apply(Observable observable, Observer observer) throws Exception {
        System.out.println(observable);
        return observer;
      }
    });
  }

  @After
  public void after() {
    sleep(3000);
  }
}
