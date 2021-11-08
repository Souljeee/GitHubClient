package com.soulje.githubclient.di


import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.google.gson.GsonBuilder
import com.soulje.githubclient.Network.AndroidNetworkStatus
import com.soulje.githubclient.Network.INetworkStatus
import com.soulje.githubclient.model.*
import com.soulje.githubclient.model.db.Database
import com.soulje.githubclient.model.db.RepositoryDao
import com.soulje.githubclient.model.db.UserDao
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val myModule = module {


    val DB_NAME = "database.db"

    single<Retrofit> {
        Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
    }

    single<GitHubApi> { get<Retrofit>().create(GitHubApi::class.java) }

    single<Database> { Room.databaseBuilder(get(), Database::class.java, DB_NAME).build() }

    single<UserDao> { get<Database>().userDao }

    single<RepositoryDao> { get<Database>().repositoryDao }

    single<Cicerone<Router>> { Cicerone.create() }

    single<NavigatorHolder> { get<Cicerone<Router>>().getNavigatorHolder() }

    single<Router> { get<Cicerone<Router>>().router }

    single<IGitHubUsersRepo>(named("api")) { GitHubUsersRepo() }

    single<IGitHubUsersRepo>(named("cache")) { RetrofitRepo(get(),get(),get<IGitHubUsersRepo>(named("api")),get(),get(),get()) }

    single<INetworkStatus> { AndroidNetworkStatus(get()) }

    single<IUserCache> { RoomGithubUsersCache()}

    single<IRepositoriesCache> { RoomGithubRepositoriesCache()}
}