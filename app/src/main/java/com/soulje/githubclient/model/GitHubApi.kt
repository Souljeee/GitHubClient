package com.soulje.githubclient.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("users")
    fun getUsersFromServer() : Single<List<GitHubUser>>

    @GET("users/{user}/repos")
    fun getUserReposFromServer(
        @Path("user") userLogin : String
    ): Single<List<UserRepository>>
}