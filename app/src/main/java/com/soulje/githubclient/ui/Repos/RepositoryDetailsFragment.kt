package com.soulje.githubclient.ui.Repos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soulje.githubclient.R
import com.soulje.githubclient.databinding.FragmentProfileBinding
import com.soulje.githubclient.databinding.FragmentRepositoryDetailsBinding
import com.soulje.githubclient.model.UserRepository


class RepositoryDetailsFragment : Fragment() {

    private lateinit var currentRepo:UserRepository
    private var _binding: FragmentRepositoryDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        bundle?.let {
            currentRepo = bundle.getParcelable("repoData")!!
        }
        setRepoName(currentRepo)
    }

    private fun setRepoName(repo: UserRepository) = with(binding) {
        repoName.text = repo.name
        repoForksCount.text = repo.forksCount.toString()
        repoWatchersCount.text = repo.watchersCount.toString()
        repo.language?.let{repoLanguage.text = it}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(repo: UserRepository): RepositoryDetailsFragment{
            val f = RepositoryDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable("repoData",repo)
            f.arguments = bundle
            return f
        }

    }
}