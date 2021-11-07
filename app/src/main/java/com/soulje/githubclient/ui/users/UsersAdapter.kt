package com.soulje.githubclient.ui.users

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soulje.githubclient.LikeEvent
import com.soulje.githubclient.R
import com.soulje.githubclient.app.App
import com.soulje.githubclient.databinding.UsersListItemLayoutBinding
import com.soulje.githubclient.model.GitHubUser
import com.soulje.githubclient.presenter.IUserListPresenter

class UsersAdapter(val presenter: IUserListPresenter):
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        UsersListItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
    ). apply {
        itemView.setOnClickListener{
                presenter.itemClickListener?.invoke(this)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    inner class ViewHolder(val vb: UsersListItemLayoutBinding): RecyclerView.ViewHolder(vb.root),
        UserItemView {

        override var pos = adapterPosition

        override fun setInfo(user: GitHubUser) {
            vb.userLogin.text = user.login
            if(user.like){
                vb.like.setImageResource(R.drawable.ic_baseline_favorite_red_24)
            }
        }

        override fun setLikes(user: GitHubUser) {
            App.instance.likeBus.get()
                .subscribe {
                    if(it is LikeEvent){
                        if(it.pos == pos){
                            user.like = true
                            vb.like.setImageResource(R.drawable.ic_baseline_favorite_red_24)
                        }
                    }
                }
        }

    }
}