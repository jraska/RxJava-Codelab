package com.jraska.rx.codelab.http

data class UserWithRepos(
  val user: User,
  val repos: List<Repo>
)
