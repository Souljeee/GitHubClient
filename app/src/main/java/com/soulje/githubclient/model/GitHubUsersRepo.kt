package com.soulje.githubclient.model

import io.reactivex.rxjava3.core.Flowable.interval
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.interval
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class GitHubUsersRepo {

    private val repositories = listOf(
        GitHubUser("Login1", false),
        GitHubUser("Login2", false),
        GitHubUser("Login3", false),
        GitHubUser("Login4", false),
        GitHubUser("Login5", false)
    )

    fun getUsers():Single<List<GitHubUser>>{
        //return Observable.just(repositories)
        //return Observable.interval(5,TimeUnit.SECONDS).map { repositories }.take(1)
        return Single.fromCallable { return@fromCallable repositories }.delay(5,TimeUnit.SECONDS)
    }
}