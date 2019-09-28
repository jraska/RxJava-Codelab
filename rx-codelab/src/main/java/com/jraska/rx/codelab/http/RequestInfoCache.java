package com.jraska.rx.codelab.http;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Showcase purpose - not a cache at all :P
 */
public class RequestInfoCache {
  public static Observable<RequestInfo> getRequestInfo() {
    return Observable.fromCallable(() -> getRequestSync())
      .delay(400, TimeUnit.MILLISECONDS);
  }

  private static RequestInfo getRequestSync() {
    RequestInfo requestInfo = new RequestInfo();
    requestInfo.origin = "cache";

    return requestInfo;
  }
}
