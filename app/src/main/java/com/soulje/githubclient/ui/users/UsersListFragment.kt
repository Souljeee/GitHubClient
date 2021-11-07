package com.soulje.githubclient.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.soulje.githubclient.Network.AndroidNetworkStatus
import com.soulje.githubclient.app.App
import com.soulje.githubclient.databinding.FragmentProfileBinding
import com.soulje.githubclient.databinding.FragmentUsersListBinding
import com.soulje.githubclient.model.GitHubUsersRepo
import com.soulje.githubclient.model.RetrofitRepo
import com.soulje.githubclient.model.RoomGithubRepositoriesCache
import com.soulje.githubclient.model.RoomGithubUsersCache
import com.soulje.githubclient.model.db.Database
import com.soulje.githubclient.presenter.UsersPresenter
import com.soulje.githubclient.ui.navigator.BackButtonListener
import com.soulje.githubclient.ui.navigator.AndroidScreens
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersListFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private val presenter: UsersPresenter by moxyPresenter { UsersPresenter(
        RetrofitRepo(GitHubUsersRepo(), AndroidNetworkStatus(requireContext()),
        Database.getInstance(),RoomGithubUsersCache(), RoomGithubRepositoriesCache()), App.instance.router, AndroidScreens()) }
    private lateinit var adapter: UsersAdapter

    private var _binding: FragmentUsersListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {

        _binding = FragmentUsersListBinding.inflate(inflater,container,false)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance() = UsersListFragment()
    }
}