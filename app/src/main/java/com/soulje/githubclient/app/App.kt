package com.soulje.githubclient.app

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.soulje.githubclient.di.myModule
import com.soulje.githubclient.model.GitHubApi
import com.soulje.githubclient.model.db.Database
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(myModule)
        }
    }

}

