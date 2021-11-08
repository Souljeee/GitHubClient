package com.soulje.githubclient.model

import com.soulje.githubclient.Network.INetworkStatus
import com.soulje.githubclient.model.db.*
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class RetrofitRepo(
    val userDao: UserDao,
    val repoDao: RepositoryDao,
    val api: IGitHubUsersRepo,
    val networkStatus: INetworkStatus,
    val usersCache: IUserCache,
    val repoCache: IRepositoriesCache
) : IGitHubUsersRepo {


    override fun getUsers(): Single<List<GitHubUser>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers()
                    .flatMap { users ->
                        usersCache.toCache(users = users, userDao = userDao)
                    }
            } else {
                Single.fromCallable {
                    userDao.getAll().map { roomUser ->
                        GitHubUser(id = roomUser.id, login = roomUser.login, avatarUrl = roomUser.avatarUrl)
                    }
                }
            }
        }.subscribeOn(Schedulers.io())




    override fun getRepos(login: String) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            login.let { login ->
                api.getRepos(login)
                    .flatMap { repositories ->
                        repoCache.toCache(login = login, repositories = repositories, userDao = userDao,repoDao = repoDao)
                    }
            } ?: Single.error<List<UserRepository>>(RuntimeException("User has no repos url")).subscribeOn(
                Schedulers.io())
        } else {
            Single.fromCallable {
                val roomUser = login.let { userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                repoDao.findForUser(roomUser.id).map { UserRepository(id = it.id, name = it.name, forksCount = it.forksCount) }
            }

        }
    }.subscribeOn(Schedulers.io())
}