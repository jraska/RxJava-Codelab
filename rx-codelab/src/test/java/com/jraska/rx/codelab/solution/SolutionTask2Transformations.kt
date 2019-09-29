package com.jraska.rx.codelab.solution

import com.jraska.rx.codelab.RxLogging
import com.jraska.rx.codelab.http.GitHubConverter
import com.jraska.rx.codelab.http.HttpModule
import org.junit.Before
import org.junit.Test

class SolutionTask2Transformations {
  private val gitHubApi = HttpModule.mockedGitHubApi()

  @Before
  fun setUp() {
    RxLogging.enableObservableSubscribeLogging()
  }

  @Test
  fun map_convertUserDto() {
    gitHubApi.getUser(LOGIN)
      .map { GitHubConverter.convert(it) }
      .map { it.toString() }
      .subscribe { println(it) }
  }

  @Test
  fun flatMap_getFirstUserDetailAfterGettingList() {
    gitHubApi.getFirstUsers()
      .flatMap { gitHubUsers -> gitHubApi.getUser(gitHubUsers[0].login) }
      .map { GitHubConverter.convert(it) }
      .subscribe { println(it) }
  }

  @Test
  fun replayAutoConnect_oneRequestForTwoSubscriptions() {
    val observable = gitHubApi.getUser(LOGIN)
      .map { GitHubConverter.convert(it) }
      .replay(1)
      .autoConnect()

    observable.subscribe { println(it) }
    observable.subscribe { println(it) }
  }

  companion object {
    private const val LOGIN = "defunkt"
  }
}
