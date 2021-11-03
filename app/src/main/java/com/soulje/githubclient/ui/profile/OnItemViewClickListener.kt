package com.soulje.githubclient.ui.profile

import com.soulje.githubclient.model.UserRepository

interface OnItemViewClickListener {
    fun onItemViewClick(repo: UserRepository)
}