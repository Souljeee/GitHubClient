package com.soulje.githubclient.model

import com.soulje.githubclient.model.db.Database
import io.reactivex.rxjava3.core.Single

interface IRepositoriesCache {
    fun toCache(login: String,repositories: List<UserRepository>,db: Database): Single<List<UserRepository>>
}