package com.soulje.githubclient.view.screens

import com.github.terrakok.cicerone.Screen

interface IScreen {
    fun users(): Screen
    fun profile(pos:Int): Screen
}