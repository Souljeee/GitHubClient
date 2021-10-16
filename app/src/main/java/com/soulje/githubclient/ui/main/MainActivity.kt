package com.soulje.githubclient.ui.main

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.soulje.githubclient.R
import com.soulje.githubclient.app.App
import com.soulje.githubclient.databinding.ActivityMainBinding
import com.soulje.githubclient.presenter.MainPresenter
import com.soulje.githubclient.ui.navigator.AndroidScreens
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { MainPresenter(App.instance.router, AndroidScreens()) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

}