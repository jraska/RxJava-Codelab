package com.jraska.rx.codelab.solution;

import com.jraska.rx.codelab.RxLogging;
import com.jraska.rx.codelab.forest.Forest;
import com.jraska.rx.codelab.forest.Log;
import com.jraska.rx.codelab.forest.Lumberjack;
import com.jraska.rx.codelab.forest.Tools;
import com.jraska.rx.codelab.furniture.Carpenter;
import com.jraska.rx.codelab.furniture.Chair;
import com.jraska.rx.codelab.furniture.Parts;
import com.jraska.rx.codelab.furniture.Rivet;
import com.jraska.rx.codelab.furniture.Screw;
import com.jraska.rx.codelab.furniture.Sofa;
import com.jraska.rx.codelab.furniture.Table;

import com.jraska.rx.codelab.http.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;

public class Solution_Task3_Combining {
  private static final String LOGIN = "defunkt";
  private GitHubApi gitHubApi = HttpModule.mockedGitHubApi();

  @Before
  public void setUp() {
    RxLogging.enableObservableSubscribeLogging();
  }

  @Test
  public void zipWith_userWithRepos() {
    gitHubApi.getUser(LOGIN)
      .zipWith(gitHubApi.getRepos(LOGIN), GitHubConverter::convert)
      .subscribe(System.out::println);
  }

  @Test
  public void startWith_userInCache() {
    gitHubApi.getUser(LOGIN)
      .map(GitHubConverter::convert)
      .startWith(UserCache.getUser(LOGIN))
      .subscribe(System.out::println);
  }

  @Test
  public void merge_userInCache() {
    UserCache.getUser(LOGIN)
      .mergeWith(gitHubApi.getUser(LOGIN)
        .map(GitHubConverter::convert))
      .subscribe(System.out::println);
  }

  @Test
  public void combineLatest_cachedUserWithRepos() {
    Observable<User> userObservable = gitHubApi.getUser(LOGIN)
      .map(GitHubConverter::convert)
      .startWith(UserCache.getUser(LOGIN));

    Observable<List<Repo>> reposObservable = gitHubApi.getRepos(LOGIN)
      .map(GitHubConverter::convert);

    Observable.combineLatest(reposObservable, userObservable, GitHubConverter::convert)
      .subscribe(System.out::println);
  }
}
