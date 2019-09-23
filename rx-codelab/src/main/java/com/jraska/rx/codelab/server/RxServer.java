package com.jraska.rx.codelab.server;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface RxServer {

  Observable<Log> debugLogsHot();

  Flowable<Log> allLogsHot();
}
