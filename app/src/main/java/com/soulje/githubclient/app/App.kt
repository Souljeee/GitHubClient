package com.soulje.githubclient.app

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.soulje.githubclient.model.GitHubApi
import com.soulje.githubclient.model.db.Database
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {


    val retrofit  = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(GitHubApi::class.java)

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)
        startKoin {
            androidContext(this@App)
            modules()
        }
    }
    companion object {
        lateinit var instance: App
    }
}

val Fragment.app: App
    get() = requireContext().app

val Context.app: App
    get() = applicationContext as App