package com.soulje.githubclient.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class GitHubUser(
    val login : String,
    var like : Boolean
): Parcelable