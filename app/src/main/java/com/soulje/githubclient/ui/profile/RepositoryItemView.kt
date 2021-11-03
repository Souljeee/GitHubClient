package com.soulje.githubclient.ui.profile

import com.soulje.githubclient.model.UserRepository

interface RepositoryItemView {
    var repo: UserRepository?
    fun bind(repo : UserRepository)
}