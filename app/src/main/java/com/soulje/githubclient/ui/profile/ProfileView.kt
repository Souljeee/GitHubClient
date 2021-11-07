package com.soulje.githubclient.ui.profile

import com.soulje.githubclient.model.UserRepository
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ProfileView: MvpView {
    @AddToEndSingle
    fun init(userLogin:String)
    @AddToEndSingle
    fun setRecyclerViewData(reposData: List<UserRepository>)
}