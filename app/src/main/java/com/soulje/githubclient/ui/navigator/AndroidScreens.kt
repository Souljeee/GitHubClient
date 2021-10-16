package com.soulje.githubclient.ui.navigator

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.soulje.githubclient.ui.profile.ProfileFragment
import com.soulje.githubclient.ui.users.UsersListFragment

class AndroidScreens: IScreen {
    override fun users(): Screen = FragmentScreen{ UsersListFragment.newInstance() }
    override fun profile(pos: Int): Screen = FragmentScreen{ ProfileFragment.newInstance(pos) }
}