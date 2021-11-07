package com.soulje.githubclient.model

import com.soulje.githubclient.model.db.Database
import io.reactivex.rxjava3.core.Single

interface IUserCache {
    fun toCache(users: List<GitHubUser>,db: Database): Single<List<GitHubUser>>
}