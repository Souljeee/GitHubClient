package com.soulje.githubclient.presenter

import com.soulje.githubclient.model.GitHubUsersRepo
import com.soulje.githubclient.ui.profile.ProfileView
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class ProfilePresenter(val userRepo: GitHubUsersRepo):MvpPresenter<ProfileView>() {

    private var disposable: Disposable? = null

    fun setLogin(pos:Int){
        disposable = userRepo
            .getUsers()
            .subscribe{
                viewState.setLoginText(it[pos].login)
            }
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}