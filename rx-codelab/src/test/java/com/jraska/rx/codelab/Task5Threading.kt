package com.jraska.rx.codelab

import com.jraska.rx.codelab.http.GitHubConverter
import com.jraska.rx.codelab.http.HttpModule
import org.junit.After
import org.junit.Test

class Task5Threading {

  private val gitHubApi = HttpModule.mockedGitHubApi()

  @After
  fun after() {
    HttpModule.awaitNetworkRequests()
  }

  @Test
  fun zip_subscribeOn_getUserAndHisReposInParallel() {
    val userObservable = gitHubApi.getUser(LOGIN)
    val reposObservable = gitHubApi.getRepos(LOGIN)

    // TODO: Get and print Observable<UserWithRepos> whilst running both requests in parallel
  }

  @Test
  fun zip_subscribeOn_twoUserAndReposInSerialExplicitly() {
    val userObservable = gitHubApi.getUser(LOGIN)
    val reposObservable = gitHubApi.getRepos(LOGIN)

    // TODO: Get and print Observable<UserWithRepos> whilst running both requests in serial order using `Schedulers.single()`
  }

  @Test
  fun observeOn_receivingResultsOnDifferentThreads() {
    val userObservable = gitHubApi.getUser(LOGIN).map { GitHubConverter.convert(it) }
    printWithThreadId("Test thread")

    // TODO: Get user and print him on different threads, use `observeOn`, `doOnNext` and `printWithThreadId` methods
  }

  private fun printWithThreadId(value: Any) {
    println("Thread id: " + Thread.currentThread().id + ", " + value)
  }

  companion object {
    private const val LOGIN = "defunkt" // One of GitHub founders. <3 GitHub <3
  }
}
