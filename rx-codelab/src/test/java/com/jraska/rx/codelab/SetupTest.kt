package com.jraska.rx.codelab

import com.jraska.rx.codelab.http.HttpModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.IOException

class SetupTest {
  @Test
  @Throws(IOException::class)
  fun everythingSetUp() {
    val requestInfo = HttpModule.httpBinApi()
      .getRequest()
      .blockingFirst()

    assertThat(requestInfo.url).isNotNull()
    assertThat(requestInfo.origin).isNotNull()
    assertThat(requestInfo.headers).isNotEmpty
  }
}
