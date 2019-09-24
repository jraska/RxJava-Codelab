package com.jraska.rx.codelab.http;

import com.jraska.rx.codelab.SchedulerProvider;
import io.reactivex.Observable;

public class IpViewModel {
  private final HttpBinApi httpBinApi;
  private final SchedulerProvider schedulerProvider;

  public IpViewModel(HttpBinApi httpBinApi, SchedulerProvider schedulerProvider) {
    this.httpBinApi = httpBinApi;
    this.schedulerProvider = schedulerProvider;
  }

  public Observable<String> ip() {
    return httpBinApi.getRequest()
      .subscribeOn(schedulerProvider.getIo())
      .observeOn(schedulerProvider.getMain())
      .map(requestInfo -> requestInfo.origin);
  }
}
