package com.soulje.githubclient.model

import io.reactivex.rxjava3.core.Single

interface IGitHubUsersRepo {
    fun getUsers(): Single<List<GitHubUser>>
    fun getRepos(userLogin:String): Single<List<UserRepository>>
}