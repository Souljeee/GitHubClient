package com.soulje.githubclient.model

import com.soulje.githubclient.model.db.Database
import com.soulje.githubclient.model.db.RepositoryDao
import com.soulje.githubclient.model.db.RoomGithubRepository
import com.soulje.githubclient.model.db.UserDao
import io.reactivex.rxjava3.core.Single

class RoomGithubRepositoriesCache : IRepositoriesCache {
    override fun toCache(login: String,repositories: List<UserRepository>,repoDao: RepositoryDao, userDao: UserDao): Single<List<UserRepository>> {
        return Single.fromCallable {
            val roomUser = login.let { userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
            val roomRepos = repositories.map { RoomGithubRepository(id = it.id ?: "", name = it.name ?: "", forksCount = it.forksCount ?: 0, userId = roomUser.id) }
            repoDao.insert(roomRepos)
            repositories
        }
    }
}