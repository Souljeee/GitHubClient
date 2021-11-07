package com.soulje.githubclient.model

import com.soulje.githubclient.app.App
import io.reactivex.rxjava3.core.Flowable.interval
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.interval
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class GitHubUsersRepo:IGitHubUsersRepo {

    private val repositories = listOf(
        GitHubUser("Login1"),
        GitHubUser("Login2"),
        GitHubUser("Login3"),
        GitHubUser("Login4"),
        GitHubUser("Login5")
    )

    override fun getUsers():Single<List<GitHubUser>>{
        return  App.instance.api.getUsersFromServer().subscribeOn(Schedulers.io())
    }

    override fun getRepos(userLogin : String): Single<List<UserRepository>> {
        return App.instance.api.getUserReposFromServer(userLogin).subscribeOn((Schedulers.io()))
    }
}