package com.soulje.githubclient.ui.users

import com.soulje.githubclient.model.GitHubUser

interface UserItemView: IItemView {
    fun setInfo(user:GitHubUser)
    fun setLikes(user:GitHubUser)
}