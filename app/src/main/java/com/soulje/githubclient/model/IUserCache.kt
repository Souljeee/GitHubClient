package com.soulje.githubclient.model

import com.soulje.githubclient.model.db.Database
import com.soulje.githubclient.model.db.UserDao
import io.reactivex.rxjava3.core.Single

interface IUserCache {
    fun toCache(users: List<GitHubUser>,userDao: UserDao): Single<List<GitHubUser>>
}