package com.soulje.githubclient.view.UserListFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soulje.githubclient.databinding.UsersListItemLayoutBinding
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

        override fun setLogin(text: String) {
            vb.userLogin.text = text
        }

    }
}