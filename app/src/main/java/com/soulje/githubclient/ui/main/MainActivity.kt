package com.soulje.githubclient.ui.main

import android.os.Bundle
import android.util.Log
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.soulje.githubclient.R
import com.soulje.githubclient.app.App
import com.soulje.githubclient.databinding.ActivityMainBinding
import com.soulje.githubclient.presenter.MainPresenter
import com.soulje.githubclient.ui.navigator.AndroidScreens
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

import org.koin.android.ext.android.inject

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    private val router : Router by inject()
    private val navigatorHolder: NavigatorHolder by inject()

    private lateinit var binding: ActivityMainBinding

    private val presenter by moxyPresenter { MainPresenter(router, AndroidScreens()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}