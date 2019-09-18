package com.jraska.rx.codelab.http;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface HttpBinApi {
  @GET("/get?show_env=1")
  Observable<RequestInfo> getRequest();

  @GET("/status/404")
  Observable<ResponseBody> failingGet();

  @GET("/status/200")
  Observable<ResponseBody> backupGet();

  @GET("/status/200,400,401,402,403,404,410,412")
  Observable<ResponseBody> flakeyGet();

  @POST("/post")
  Single<RequestInfo> postRequest(@Body String input);

  @PUT("/put")
  Completable putRequest(@Body String input);
}
