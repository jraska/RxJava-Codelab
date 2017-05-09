package com.jraska.rx.codelab.http;

import java.util.Collections;
import java.util.List;

public final class UserWithRepos {
  public final User user;
  public final List<Repo> repos;

  public UserWithRepos(User user, List<Repo> repos) {
    this.user = user;
    this.repos = Collections.unmodifiableList(repos);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("UserWithRepos{");
    sb.append("user=").append(user);
    sb.append(", repos=");
    for (Repo repo : repos) {
      sb.append(repo).append("/n");
    }

    sb.append('}');
    return sb.toString();
  }
}
