package com.jraska.rx.codelab.http;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface HttpBinApi {
  @GET("/get?show_env=1")
  Observable<RequestInfo> getRequest();

  @POST("/post")
  Single<RequestInfo> postRequest(@Body String input);

  @PUT("/put")
  Completable putRequest(@Body String input);
}
