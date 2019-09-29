package com.jraska.rx.codelab

import com.jraska.rx.codelab.http.HttpModule
import org.junit.Before
import org.junit.Test

class Task3Combining {
  private val gitHubApi = HttpModule.mockedGitHubApi()

  @Before
  fun setUp() {
    RxLogging.enableObservableSubscribeLogging()
  }

  @Test
  fun zipWith_userWithRepos() {
    // TODO: Use gitHubApi to get user with `LOGIN` and his repos and print them. Use `GitHubConverter::convert` as zipper
  }

  @Test
  fun startWith_userInCache() {
    // TODO: Get user with `LOGIN` and startWith a `UserCache.getUserSync(LOGIN)`, Subscribe and print both values
  }

  @Test
  fun merge_userInCache() {
    // TODO: Get user `UserCache.getUserSync(LOGIN)` and mergeWith user with `LOGIN`, Subscribe and print both values
  }

  @Test
  fun combineLatest_cachedUserWithRepos() {
    // TODO: Create observable of  `UserWithRepos` with `LOGIN` and use observable with cache from previous example - use Observable.combineLatest, GithubConverter::convert
    // TODO: Print the results - there should be two emission. Try to change order of passing into Observable.combineLatest - what happens?
  }

  companion object {
    private const val LOGIN = "defunkt"
  }
}
