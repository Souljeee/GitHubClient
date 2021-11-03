package com.soulje.githubclient.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soulje.githubclient.R
import com.soulje.githubclient.databinding.ReposLayoutItemBinding
import com.soulje.githubclient.databinding.UsersListItemLayoutBinding
import com.soulje.githubclient.model.GitHubUser
import com.soulje.githubclient.model.UserRepository
import com.soulje.githubclient.presenter.ProfilePresenter
import com.soulje.githubclient.ui.users.UserItemView

class ProfileAdapter(val presenter : ProfilePresenter): RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    private lateinit var holder: ViewHolder
    private lateinit var repositories: List<UserRepository>

    fun setData(repositories: List<UserRepository>){
        this.repositories = repositories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.repos_layout_item,parent,false)
        holder = ViewHolder(
            ReposLayoutItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    inner class ViewHolder(val vb: ReposLayoutItemBinding): RecyclerView.ViewHolder(vb.root), RepositoryItemView{

        override var repo: UserRepository? = null


        override fun bind(repo : UserRepository){
            vb.reposName.text = repo.name
            this.repo = repo
        }
    }


}