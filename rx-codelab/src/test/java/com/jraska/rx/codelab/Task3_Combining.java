package com.jraska.rx.codelab;

import com.jraska.rx.codelab.forest.Forest;
import com.jraska.rx.codelab.forest.Log;
import com.jraska.rx.codelab.forest.Lumberjack;
import com.jraska.rx.codelab.forest.Tools;
import com.jraska.rx.codelab.furniture.Chair;
import com.jraska.rx.codelab.furniture.Sofa;
import com.jraska.rx.codelab.furniture.Table;
import com.jraska.rx.codelab.http.*;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class Task3_Combining {

  private static final String LOGIN = "defunkt";
  private GitHubApi gitHubApi = HttpModule.mockedGitHubApi();

  @Before
  public void setUp() {
    RxLogging.enableObservableSubscribeLogging();
  }

  @Test
  public void zipWith_userWithRepos() {
    // TODO: Use gitHubApi to get user with `LOGIN` and his repos and print them. Use `GitHubConverter::convert` as zipper
  }

  @Test
  public void startWith_userInCache() {
    // TODO: Get user with `LOGIN` and startWith a `UserCache.getUserSync(LOGIN)`, Subscribe and print both values
  }

  @Test
  public void merge_userInCache() {
    // TODO: Get user `UserCache.getUserSync(LOGIN)` and mergeWith user with `LOGIN`, Subscribe and print both values
  }

  @Test
  public void combineLatest_cachedUserWithRepos() {
    // TODO: Create observable of  `UserWithRepos` with `LOGIN` and use observable with cache from previous example - use Observable.combineLatest, GithubConverter::convert
    // TODO: Print the results - there should be two emission. Try to change order of passing into Observable.combineLatest - what happens?
  }
}
