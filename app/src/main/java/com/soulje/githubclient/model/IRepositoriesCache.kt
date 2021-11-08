package com.soulje.githubclient.model

import com.soulje.githubclient.model.db.Database
import com.soulje.githubclient.model.db.RepositoryDao
import com.soulje.githubclient.model.db.UserDao
import io.reactivex.rxjava3.core.Single

interface IRepositoriesCache {
    fun toCache(login: String, repositories: List<UserRepository>, repoDao: RepositoryDao, userDao: UserDao): Single<List<UserRepository>>
}