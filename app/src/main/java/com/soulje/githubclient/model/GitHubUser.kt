package com.soulje.githubclient.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class GitHubUser(
    @SerializedName("node_id")
    val id : String? = null,
    @SerializedName("login")
    val login : String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null
): Parcelable