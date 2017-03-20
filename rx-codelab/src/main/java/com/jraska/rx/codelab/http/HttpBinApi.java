package com.jraska.rx.codelab.http;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HttpBinApi {
    @GET("/get")
    Observable<RequestInfo> getRequest();
}
