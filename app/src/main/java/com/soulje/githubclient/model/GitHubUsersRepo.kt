package com.soulje.githubclient.model

import io.reactivex.rxjava3.core.Observable

class GitHubUsersRepo {

    private val repositories = listOf(
        GitHubUser("Login1"),
        GitHubUser("Login2"),
        GitHubUser("Login3"),
        GitHubUser("Login4"),
        GitHubUser("Login5")
    )

    fun getUsers():Observable<List<GitHubUser>>{
        return Observable.just(repositories)
    }

    /*fun getUsers():Observable<List<GitHubUser>>{
        return Observable.just(repositories)
    }*/


    /*val users: Observable<List<GitHubUser>>
        get() = */
}