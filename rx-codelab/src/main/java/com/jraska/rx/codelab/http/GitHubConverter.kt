package com.jraska.rx.codelab.http

/**
 * Many combination here to avoid pain with finding proper order of observables.
 */
object GitHubConverter {
  fun convert(gitHubUser: GitHubUser): User {
    val isAdmin = gitHubUser.siteAdmin ?: false
    return User(gitHubUser.login, gitHubUser.avatarUrl, isAdmin, gitHubUser.htmlUrl)
  }

  fun convert(gitHubRepo: GitHubRepo): Repo {
    return Repo(gitHubRepo.name, gitHubRepo.description ?: "",
      gitHubRepo.stargazersCount, gitHubRepo.forks, gitHubRepo.size)
  }

  fun convert(gitHubRepos: List<GitHubRepo>): List<Repo> {
    return gitHubRepos.map { convert(it) }
  }

  fun convert(gitHubUser: GitHubUser, gitHubRepos: List<GitHubRepo>): UserWithRepos {
    return UserWithRepos(convert(gitHubUser), convert(gitHubRepos))
  }

  fun convert(gitHubRepos: List<Repo>, user: User): UserWithRepos {
    return UserWithRepos(user, gitHubRepos)
  }

  fun convert(user: User, repos: List<Repo>): UserWithRepos {
    return UserWithRepos(user, repos)
  }
}
