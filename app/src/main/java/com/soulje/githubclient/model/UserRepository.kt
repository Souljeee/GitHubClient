package com.soulje.githubclient.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepository(
    @SerializedName("node_id")
    val id:String? = null,
    @SerializedName("name")
    val name:String? = null,
    @SerializedName("forks_count")
    val forksCount:Int = 0,
    @SerializedName("watchers_count")
    val watchersCount:Int = 0,
    @SerializedName("language")
    val language: String? = null
) : Parcelable
