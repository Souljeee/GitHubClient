package com.soulje.githubclient.ui.profile

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ProfileView: MvpView {
    @AddToEndSingle
    fun initLogin(pos:Int)
    @AddToEndSingle
    fun setLoginText(tex:String)
}