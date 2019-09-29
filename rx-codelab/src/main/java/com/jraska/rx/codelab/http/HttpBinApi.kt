package com.jraska.rx.codelab.http

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface HttpBinApi {
  @GET("/get?show_env=1")
  fun getRequest(): Observable<RequestInfo>

  @GET("/status/404")
  fun failingGet(): Observable<ResponseBody>

  @GET("/status/200")
  fun backupGet(): Observable<ResponseBody>

  @GET("/status/200,400,401,402,403,404,410,412")
  fun flakyGet(): Observable<ResponseBody>

  @POST("/post")
  fun postRequest(@Body input: String): Single<RequestInfo>

  @PUT("/put")
  fun putRequest(@Body input: String): Completable
}
