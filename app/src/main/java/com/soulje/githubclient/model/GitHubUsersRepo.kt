package com.soulje.githubclient.model

import com.soulje.githubclient.app.App
import io.reactivex.rxjava3.core.Flowable.interval
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.interval
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.java.KoinJavaComponent.inject
import java.util.concurrent.TimeUnit

class GitHubUsersRepo(private val api : GitHubApi):IGitHubUsersRepo {

    override fun getUsers():Single<List<GitHubUser>>{
        return  api.getUsersFromServer().subscribeOn(Schedulers.io())
    }

    override fun getRepos(userLogin : String): Single<List<UserRepository>> {
        return api.getUserReposFromServer(userLogin).subscribeOn((Schedulers.io()))
    }
}