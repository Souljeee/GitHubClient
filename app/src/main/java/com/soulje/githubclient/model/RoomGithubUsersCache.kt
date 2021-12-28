package com.soulje.githubclient.model

import com.soulje.githubclient.model.db.Database
import com.soulje.githubclient.model.db.RoomGithubUser
import com.soulje.githubclient.model.db.UserDao
import io.reactivex.rxjava3.core.Single

class RoomGithubUsersCache : IUserCache {
    override fun toCache(users: List<GitHubUser>,userDao: UserDao) : Single<List<GitHubUser>>{
        return Single.fromCallable {
            val roomUsers = users.map { user -> RoomGithubUser(id = user.id ?: "", login = user.login ?: "", avatarUrl = user.avatarUrl ?: "") }
            userDao.insert(roomUsers)
            users
        }
    }
}