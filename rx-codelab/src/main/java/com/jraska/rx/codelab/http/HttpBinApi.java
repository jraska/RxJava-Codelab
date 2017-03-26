package com.jraska.rx.codelab.http;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HttpBinApi {
  @GET("/get")
  Observable<RequestInfo> getRequest();

  @POST("/post")
  Observable<RequestInfo> postRequest(@Body String input);
}
