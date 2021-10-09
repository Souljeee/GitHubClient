package com.soulje.githubclient.view.screens

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.soulje.githubclient.view.ProfileFrgment.ProfileFragment
import com.soulje.githubclient.view.UserListFragment.UsersListFragment

class AndroidScreens: IScreen {
    override fun users(): Screen = FragmentScreen{ UsersListFragment.newInstance() }
    override fun profile(pos: Int): Screen = FragmentScreen{ ProfileFragment.newInstance(pos) }
}