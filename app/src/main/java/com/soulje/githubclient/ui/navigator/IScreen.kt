package com.soulje.githubclient.ui.navigator

import com.github.terrakok.cicerone.Screen
import com.soulje.githubclient.model.UserRepository

interface IScreen {
    fun users(): Screen
    fun profile(userLogin: String): Screen
    fun repository(repo: UserRepository): Screen
}