package com.soulje.githubclient.ui.users

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface UsersView: MvpView {
    @AddToEndSingle
    fun init()
    @AddToEndSingle
    fun updateList()
}