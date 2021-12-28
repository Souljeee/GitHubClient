package com.soulje.githubclient

import com.github.terrakok.cicerone.Router
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.soulje.githubclient.model.GitHubUser
import com.soulje.githubclient.model.IGitHubUsersRepo
import com.soulje.githubclient.presenter.UsersPresenter
import com.soulje.githubclient.ui.navigator.IScreen
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PresenterTest {

    private lateinit var presenter : UsersPresenter

    @Mock
    private lateinit var screen : IScreen

    @Mock
    private lateinit var usersRepo: IGitHubUsersRepo

    @Mock
    private lateinit var router: Router

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = UsersPresenter(usersRepo,router,screen)
    }

    @Test
    fun backPressed_Test(){
        presenter.backPressed()

        Mockito.verify(router, times(1)).exit()
    }

    @Test
    fun loadData_Test(){
        Mockito.`when`(usersRepo.getUsers()).thenReturn(Single.just(listOf(GitHubUser())))

        presenter.loadData()

        Mockito.verify(usersRepo, times(1)).getUsers()
    }
}