package com.soulje.githubclient.model

class GitHubUsersRepo {

    private val repositories = listOf(
        GitHubUser("Login1"),
        GitHubUser("Login2"),
        GitHubUser("Login3"),
        GitHubUser("Login4"),
        GitHubUser("Login5")
    )

    fun getUsers():List<GitHubUser>{
        return repositories
    }
}