package com.jraska.rx.codelab.http;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HttpBinApi {
    @GET("/get?show_env=1")
    Observable<RequestInfo> getRequest();

    @POST("/post")
    Observable<RequestInfo> postRequest(@Body String input);
}
