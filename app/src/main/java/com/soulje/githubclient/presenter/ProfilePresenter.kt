package com.soulje.githubclient.presenter

import com.github.terrakok.cicerone.Router
import com.soulje.githubclient.model.GitHubUsersRepo
import com.soulje.githubclient.model.IGitHubUsersRepo
import com.soulje.githubclient.model.RetrofitRepo
import com.soulje.githubclient.ui.navigator.IScreen
import com.soulje.githubclient.ui.profile.ProfileView
import com.soulje.githubclient.ui.profile.RepositoryItemView
import com.soulje.githubclient.ui.users.UserItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class ProfilePresenter(val userRepo: IGitHubUsersRepo, val router: Router, val screen: IScreen):MvpPresenter<ProfileView>() {

    var itemClickListener: ((RepositoryItemView) -> Unit)? = null

    private var disposable: Disposable? = null


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        itemClickListener = {  itemView ->
            router.navigateTo(screen.repository(itemView.repo!!))
        }
    }

    fun loadData(userLogin: String){
        disposable = userRepo
            .getRepos(userLogin)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ s->
                viewState.setRecyclerViewData(s)
            }
    }


    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}