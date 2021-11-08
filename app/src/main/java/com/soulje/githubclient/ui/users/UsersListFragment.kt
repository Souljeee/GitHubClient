package com.soulje.githubclient.ui.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import com.soulje.githubclient.Network.AndroidNetworkStatus
import com.soulje.githubclient.Network.INetworkStatus
import com.soulje.githubclient.app.App
import com.soulje.githubclient.databinding.FragmentProfileBinding
import com.soulje.githubclient.databinding.FragmentUsersListBinding
import com.soulje.githubclient.model.*
import com.soulje.githubclient.model.db.Database
import com.soulje.githubclient.model.db.RepositoryDao
import com.soulje.githubclient.model.db.UserDao
import com.soulje.githubclient.presenter.UsersPresenter
import com.soulje.githubclient.ui.navigator.BackButtonListener
import com.soulje.githubclient.ui.navigator.AndroidScreens
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class UsersListFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private val router: Router by inject()
    private val retrofitRepo: IGitHubUsersRepo by inject(named("cache"))


    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            retrofitRepo,
            router,
            AndroidScreens()
        )
    }


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