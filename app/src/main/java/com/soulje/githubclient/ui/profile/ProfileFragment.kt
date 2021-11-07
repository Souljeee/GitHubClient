package com.soulje.githubclient.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soulje.githubclient.LikeEvent
import com.soulje.githubclient.R
import com.soulje.githubclient.app.App
import com.soulje.githubclient.databinding.FragmentProfileBinding
import com.soulje.githubclient.model.GitHubUsersRepo
import com.soulje.githubclient.presenter.ProfilePresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    private lateinit var binding : FragmentProfileBinding
    private val presenter: ProfilePresenter by moxyPresenter { ProfilePresenter(GitHubUsersRepo()) }
    private var pos: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        bundle?.let {
            pos = it.getInt("key")
        }
        initLogin(pos)
        initLike()
    }

    override fun initLogin(pos:Int) = with(binding) {
        if(pos != -1){
            presenter.setInfo(pos)
        }
    }

    override fun setLoginText(tex: String) = with(binding){
        login.text = tex
    }


    override fun initLike() = with(binding) {
        var isPressed = false
        like.setOnClickListener {
            if(!isPressed){
                isPressed = true
                App.instance.likeBus.post(LikeEvent(pos))
                like.setImageResource(R.drawable.ic_baseline_favorite_red_24)
            }
        }
    }

    companion object {
        fun newInstance(pos:Int) : ProfileFragment {
            val f: ProfileFragment = ProfileFragment()
            val bundle = Bundle()
            bundle.putInt("key",pos)
            f.arguments = bundle
            return f
        }
    }
}