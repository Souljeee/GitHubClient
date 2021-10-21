package com.soulje.githubclient.app

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.soulje.githubclient.EventBus

class App : Application() {

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val likeBus = EventBus()
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        lateinit var instance: App
    }
}

val Fragment.app: App
    get() = requireContext().app

val Context.app: App
    get() = applicationContext as App