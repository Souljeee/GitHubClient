package com.soulje.githubclient.ui.navigator

import com.github.terrakok.cicerone.Screen

interface IScreen {
    fun users(): Screen
    fun profile(pos:Int): Screen
}