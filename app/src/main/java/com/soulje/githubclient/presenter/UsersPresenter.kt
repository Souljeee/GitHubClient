package com.soulje.githubclient.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.soulje.githubclient.model.GitHubUser
import com.soulje.githubclient.model.GitHubUsersRepo
import com.soulje.githubclient.ui.navigator.IScreen
import com.soulje.githubclient.ui.users.UserItemView
import com.soulje.githubclient.ui.users.UsersView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(val usersRepo: GitHubUsersRepo, val router: Router, val screen: IScreen) :
    MvpPresenter<UsersView>() {

    private var disposable:Disposable? = null

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setInfo(user)
            view.setLikes(user)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screen.profile(itemView.pos))
        }
    }

    private fun loadData() {
        disposable = usersRepo
            .getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { s ->
                usersListPresenter.users.addAll(s)
                viewState.updateList()
            }

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }

}