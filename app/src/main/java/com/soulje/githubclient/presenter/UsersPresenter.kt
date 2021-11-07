package com.soulje.githubclient.presenter

import com.github.terrakok.cicerone.Router
import com.soulje.githubclient.model.GitHubUser
import com.soulje.githubclient.model.GitHubUsersRepo
import com.soulje.githubclient.view.screens.IScreen
import com.soulje.githubclient.view.UserListFragment.UserItemView
import com.soulje.githubclient.view.UserListFragment.UsersView
import moxy.MvpPresenter

class UsersPresenter(val usersRepo: GitHubUsersRepo, val router: Router,val screen : IScreen) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
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
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}