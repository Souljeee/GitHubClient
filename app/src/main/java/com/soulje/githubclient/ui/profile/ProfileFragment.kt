package com.soulje.githubclient.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import com.soulje.githubclient.Network.AndroidNetworkStatus
import com.soulje.githubclient.Network.INetworkStatus
import com.soulje.githubclient.R
import com.soulje.githubclient.app.App
import com.soulje.githubclient.databinding.FragmentProfileBinding
import com.soulje.githubclient.model.*
import com.soulje.githubclient.model.db.Database
import com.soulje.githubclient.model.db.RepositoryDao
import com.soulje.githubclient.model.db.UserDao
import com.soulje.githubclient.presenter.ProfilePresenter
import com.soulje.githubclient.ui.navigator.AndroidScreens
import com.soulje.githubclient.ui.users.UsersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named


class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val router : Router by inject()

    private val retrofitRepo: IGitHubUsersRepo by inject(named("cache"))

    private val presenter: ProfilePresenter by moxyPresenter { ProfilePresenter( retrofitRepo, router, AndroidScreens()) }
    private lateinit var userLogin:String
    private lateinit var adapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        bundle?.let {
            userLogin = it.getString("key")!!
        }
        init(userLogin)
    }

    override fun init(userLogin: String) = with(binding) {
        presenter.loadData(userLogin)
    }

    override fun setRecyclerViewData(reposData: List<UserRepository>) = with(binding){
        repostoriesList.layoutManager = LinearLayoutManager(context)
        adapter = ProfileAdapter(presenter)
        adapter.setData(reposData)
        repostoriesList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    companion object {
        fun newInstance(userLogin:String) : ProfileFragment {
            val f: ProfileFragment = ProfileFragment()
            val bundle = Bundle()
            bundle.putString("key",userLogin)
            f.arguments = bundle
            return f
        }
    }
}