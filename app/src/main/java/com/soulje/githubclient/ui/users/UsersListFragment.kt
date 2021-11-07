package com.soulje.githubclient.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.soulje.githubclient.app.App
import com.soulje.githubclient.databinding.FragmentUsersListBinding
import com.soulje.githubclient.model.GitHubUsersRepo
import com.soulje.githubclient.presenter.UsersPresenter
import com.soulje.githubclient.ui.navigator.BackButtonListener
import com.soulje.githubclient.ui.navigator.AndroidScreens
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersListFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private val presenter: UsersPresenter by moxyPresenter { UsersPresenter(GitHubUsersRepo(), App.instance.router, AndroidScreens()) }
    private lateinit var adapter: UsersAdapter

    private lateinit var binding: FragmentUsersListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {

        binding = FragmentUsersListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun init() = with(binding) {
        users.layoutManager = LinearLayoutManager(context)
        adapter = UsersAdapter(presenter.usersListPresenter)
        users.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()


    companion object {
        fun newInstance() = UsersListFragment()
    }
}