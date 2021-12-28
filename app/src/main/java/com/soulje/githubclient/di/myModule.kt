package com.soulje.githubclient.di


import android.content.Context
import android.provider.ContactsContract
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
import com.soulje.githubclient.ui.main.MainActivity
import com.soulje.githubclient.ui.profile.ProfileFragment
import com.soulje.githubclient.ui.users.UsersListFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
class myModule(val context: Context){

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitApi(retrofit: Retrofit): GitHubApi{
        return retrofit.create(GitHubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDataBase(context: Context): Database{
        return Room.databaseBuilder(context, Database::class.java, "database.db").build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: Database) : UserDao{
        return db.userDao
    }

    @Provides
    @Singleton
    fun provideRepositoryDao(db: Database) : RepositoryDao{
        return db.repositoryDao
    }

    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<Router>{
        return Cicerone.create()
    }

    @Provides
    @Singleton
    fun provideNavigatorHolder(cicerone: Cicerone<Router>):NavigatorHolder{
        return cicerone.getNavigatorHolder()
    }

    @Provides
    @Singleton
    fun provideRouter(cicerone: Cicerone<Router>): Router{
        return cicerone.router
    }

    @Provides
    @Singleton
    @Named("api")
    fun provideApiRepo(api: GitHubApi):IGitHubUsersRepo{
        return GitHubUsersRepo(api)
    }


    @Provides
    @Singleton
    @Named("memory")
    fun provideCacheRepo(
        userDao: UserDao,
        repoDao: RepositoryDao,
        @Named("api")api: IGitHubUsersRepo,
        networkStatus: INetworkStatus,
        usersCache: IUserCache,
        repoCache: IRepositoriesCache
    ):IGitHubUsersRepo{
        return RetrofitRepo(
            userDao = userDao,
            repoDao = repoDao,
            api = api,
            networkStatus = networkStatus,
            usersCache = usersCache,
            repoCache = repoCache
        )
    }

    @Provides
    @Singleton
    fun provideNetworkStatus(context: Context):INetworkStatus{
        return AndroidNetworkStatus(context)
    }

    @Provides
    @Singleton
    fun provideUserCache():IUserCache{
        return RoomGithubUsersCache()
    }

    @Provides
    @Singleton
    fun provideRepositoryCache():IRepositoriesCache{
        return RoomGithubRepositoriesCache()
    }

}

@Component(modules = [myModule::class])
@Singleton
interface AppComponent{
    fun inject(activity: MainActivity)
    fun inject(fragment: UsersListFragment)
    fun inject(fragment: ProfileFragment)
}