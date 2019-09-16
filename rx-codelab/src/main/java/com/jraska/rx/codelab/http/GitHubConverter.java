package com.jraska.rx.codelab.http;

import java.util.List;
import java.util.stream.Collectors;

public class GitHubConverter {
  public static User convert(GitHubUser gitHubUser) {
    boolean isAdmin = gitHubUser.siteAdmin == null ? false : gitHubUser.siteAdmin;
    return new User(gitHubUser.login, gitHubUser.avatarUrl, isAdmin, gitHubUser.htmlUrl);
  }

  public static Repo convert(GitHubRepo gitHubRepo) {
    return new Repo(gitHubRepo.name, gitHubRepo.description,
      gitHubRepo.stargazersCount, gitHubRepo.forks, gitHubRepo.size);
  }

  public static List<Repo> convert(List<GitHubRepo> gitHubRepos) {
    return gitHubRepos.stream()
      .map(GitHubConverter::convert)
      .collect(Collectors.toList());
  }

  public static UserWithRepos convert(GitHubUser gitHubUser, List<GitHubRepo> gitHubRepos) {
    return new UserWithRepos(convert(gitHubUser), convert(gitHubRepos));
  }
}
