package com.jraska.rx.codelab;

import com.jraska.rx.codelab.http.HttpBinApi;
import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.RequestInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Task5_IntoPractice {
  HttpBinApi httpBinApi;

  @Before
  public void before() {
    httpBinApi = HttpModule.httpBinApi();
  }

  @Test
  public void map_printRequestId() {
    // TODO: Do get request to httpbin and print out the "X-Request-Id" header UUID, there is helper method in this test for that
  }

  @Test
  public void flatMap_performAnotherRequestAfterFirstOneArrivesBack() {
    // TODO: Once the first request emits, fire another POST request and print it out
  }

  @Test
  public void zip_twoRequestsAndPrintThemTogether() {
    // TODO: Perform two get requests and print their ids
  }

  @Test
  public void zip_subscribeOn_twoRequestsInParallel() {
    // TODO: Perform two get requests and print their ids, requests should run in parallel
    // NOTE: use blockingSubscribe to stop test from finishing before the result comes back
  }

  @Test
  public void zip_subscribeOn_twoSerialRequestsWithScheduler() {
    // TODO: Use Scheduler.single() to run GET requests in serial order, but be scheduled out of current thread
  }

  @Test
  public void subscribeOn_observeOn_twoPrintRequestsFromDifferentThread() {
    // TODO: Make the requests run in parallel, but log the emits from the same thread. You can use printWithThreadId to check that
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
