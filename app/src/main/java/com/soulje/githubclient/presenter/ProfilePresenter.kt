package com.soulje.githubclient.presenter

import com.soulje.githubclient.model.GitHubUsersRepo
import com.soulje.githubclient.view.ProfileFrgment.ProfileView
import moxy.MvpPresenter

class ProfilePresenter(val userRepo: GitHubUsersRepo):MvpPresenter<ProfileView>() {

    fun setLogin(pos:Int){
        val x = userRepo
            .getUsers()
            .subscribe{
                viewState.setLoginText(it[pos].login)
            }

    }
}