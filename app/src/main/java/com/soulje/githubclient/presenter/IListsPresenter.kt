package com.soulje.githubclient.presenter

import com.soulje.githubclient.ui.users.IItemView
import com.soulje.githubclient.ui.users.UserItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>