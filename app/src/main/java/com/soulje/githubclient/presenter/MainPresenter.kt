package com.soulje.githubclient.presenter

import com.github.terrakok.cicerone.Router
import com.soulje.githubclient.view.screens.IScreen
import com.soulje.githubclient.view.MainActivity.MainView
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreen) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

}