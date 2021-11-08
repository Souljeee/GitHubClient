package com.soulje.githubclient.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.soulje.githubclient.ui.navigator.IScreen
import com.soulje.githubclient.ui.main.MainView
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreen) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

}