package com.jraska.rx.codelab;

import com.jraska.rx.codelab.http.HttpModule;
import com.jraska.rx.codelab.http.RequestInfo;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class SetupTest {
  @Test
  public void everythingSetUp() throws IOException {
    RequestInfo requestInfo = HttpModule.httpBinApi()
      .getRequest()
      .blockingFirst();

    assertThat(requestInfo.url).isNotNull();
    assertThat(requestInfo.origin).isNotNull();
    assertThat(requestInfo.headers).isNotEmpty();
  }
}
