package com.jraska.rx.codelab.http;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {
  @GET("/users/{login}")
  Observable<GitHubUser> getUser(@Path("login") String login);

  @GET("/users?since=0")
  Observable<List<GitHubUser>> getFirstUsers();

  @GET("/users/{login}/repos?type=all")
  Observable<List<GitHubRepo>> getRepos(@Path("login") String login);
}
