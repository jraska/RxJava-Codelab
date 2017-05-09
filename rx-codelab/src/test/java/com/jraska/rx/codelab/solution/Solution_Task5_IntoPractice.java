package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.RequestInfo;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.schedulers.Schedulers;

@Ignore // to avoid run those all the time and hitting http bin
public class Solution_Task5_IntoPractice {
  HttpBinApi httpBinApi;

  @Before
  public void before() {
    httpBinApi = HttpModule.httpBinApi();
  }

  @Test
  public void map_printRequestId() {
    httpBinApi.getRequest()
      .map(Solution_Task5_IntoPractice::requestId)
      .subscribe(System.out::println);
  }

  @Test
  public void flatMap_performAnotherRequestAfterFirstOneArrivesBack() {
    httpBinApi.getRequest()
      .flatMap(requestInfo -> httpBinApi.postRequest("Hello"))
      .subscribe(System.out::println);
  }

  @Test
  public void zip_twoRequestsAndPrintThemTogether() {
    httpBinApi.getRequest()
      .zipWith(httpBinApi.getRequest(), (info, info2) -> ids(info, info2))
      .subscribe(System.out::println);
  }

  @Test
  public void zip_subscribeOn_twoRequestsInParallel() {
    httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())
      .zipWith(httpBinApi.getRequest(), (info, info2) -> ids(info, info2))
      .blockingSubscribe(System.out::println);
  }

  @Test
  public void zip_subscribeOn_twoSerialRequestsWithScheduler() {
    httpBinApi.getRequest()
      .subscribeOn(Schedulers.single())
      .zipWith(httpBinApi.getRequest().subscribeOn(Schedulers.single()), (info, info2) -> ids(info, info2))
      .blockingSubscribe(System.out::println);
  }

  @Test
  public void subscribeOn_observeOn_twoPrintRequestsFromDifferentThread() {
    httpBinApi.getRequest()
      .subscribeOn(Schedulers.io())
      .observeOn(Schedulers.single())
      .doOnNext(Solution_Task5_IntoPractice::printWithThreadId)
      .zipWith(httpBinApi.getRequest()
        .subscribeOn(Schedulers.io()), (info, info2) -> ids(info, info2))
      .observeOn(Schedulers.single())
      .doOnNext(Solution_Task5_IntoPractice::printWithThreadId)
      .blockingSubscribe();
  }

  static UUID requestId(RequestInfo info) {
    return UUID.fromString(info.headers.get("X-Request-Id"));
  }

  static List<UUID> ids(RequestInfo... infos) {
    List<UUID> ids = new ArrayList<>();
    for (RequestInfo info : infos) {
      ids.add(requestId(info));
    }
    return ids;
  }

  static void printWithThreadId(Object object) {
    System.out.println(object + ", Thread id: " + Thread.currentThread().getId());
  }
}
