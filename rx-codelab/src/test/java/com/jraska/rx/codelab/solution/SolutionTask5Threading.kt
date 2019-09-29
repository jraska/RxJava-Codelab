package com.jraska.rx.codelab.solution

import com.jraska.rx.codelab.http.GitHubConverter
import com.jraska.rx.codelab.http.HttpModule
import com.jraska.rx.codelab.zipWith
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Test

class SolutionTask5Threading {

  private val gitHubApi = HttpModule.mockedGitHubApi()

  @After
  fun after() {
    HttpModule.awaitNetworkRequests()
  }

  @Test
  fun zip_subscribeOn_getUserAndHisReposInParallel() {
    gitHubApi.getUser(LOGIN)
      .subscribeOn(Schedulers.io())
      .zipWith(gitHubApi.getRepos(LOGIN), GitHubConverter::convert)
      .subscribe { println(it) }
  }

  @Test
  fun zip_subscribeOn_twoUserAndReposInSerialExplicitly() {
    gitHubApi.getUser(LOGIN)
      .subscribeOn(Schedulers.single())
      .zipWith(gitHubApi.getRepos(LOGIN).subscribeOn(Schedulers.single()), GitHubConverter::convert)
      .subscribeOn(Schedulers.io())
      .subscribe { println(it) }
  }

  @Test
  fun observeOn_receivingResultsOnDifferentThreads() {
    val userObservable = gitHubApi.getUser(LOGIN).map(GitHubConverter::convert)
    printWithThreadId("Test thread")

    userObservable.doOnNext { this.printWithThreadId(it) }
      .observeOn(Schedulers.single())
      .doOnNext { this.printWithThreadId(it) }
      .observeOn(Schedulers.computation())
      .subscribe { this.printWithThreadId(it) }
  }

  private fun printWithThreadId(`object`: Any) {
    println("Thread id: " + Thread.currentThread().id + ", " + `object`)
  }

  companion object {
    private const val LOGIN = "defunkt" // One of GitHub founders. <3 GitHub <3
  }
}
