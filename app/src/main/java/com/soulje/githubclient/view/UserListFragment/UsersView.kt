package com.soulje.githubclient.view.UserListFragment

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface UsersView: MvpView {
    @AddToEndSingle
    fun init()
    @AddToEndSingle
    fun updateList()
}