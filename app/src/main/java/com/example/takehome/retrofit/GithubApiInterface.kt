package com.example.takehome.retrofit

import com.example.takehome.model.Repo
import com.example.takehome.model.UserRepo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiInterface {
    @GET ("users/{username}")
    fun getRepoDetail(@Path("username") username: String): Observable<UserRepo>

    @GET("users/{username}/repos")
    fun fetchRepoList (@Path ("username") username: String): Observable<List<Repo>>

}