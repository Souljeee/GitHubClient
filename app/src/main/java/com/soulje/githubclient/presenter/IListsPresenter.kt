package com.soulje.githubclient.presenter

import com.soulje.githubclient.view.UserListFragment.IItemView
import com.soulje.githubclient.view.UserListFragment.UserItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>