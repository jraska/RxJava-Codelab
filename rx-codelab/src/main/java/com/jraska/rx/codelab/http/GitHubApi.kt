package com.jraska.rx.codelab.http

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

  @GET("/users?since=0")
  fun getFirstUsers(): Observable<List<GitHubUser>>

  @GET("/users/{login}")
  fun getUser(@Path("login") login: String): Observable<GitHubUser>

  @GET("/users/{login}/repos")
  fun getRepos(@Path("login") login: String): Observable<List<GitHubRepo>>
}
