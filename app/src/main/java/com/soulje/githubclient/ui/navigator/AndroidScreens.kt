package com.soulje.githubclient.ui.navigator

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.soulje.githubclient.model.UserRepository
import com.soulje.githubclient.ui.Repos.RepositoryDetailsFragment
import com.soulje.githubclient.ui.profile.ProfileFragment
import com.soulje.githubclient.ui.users.UsersListFragment

class AndroidScreens: IScreen {
    override fun users(): Screen = FragmentScreen{ UsersListFragment.newInstance() }
    override fun profile(userLogin: String): Screen = FragmentScreen{ ProfileFragment.newInstance(userLogin) }
    override fun repository(repo: UserRepository): Screen = FragmentScreen{ RepositoryDetailsFragment.newInstance(repo)}
}