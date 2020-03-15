package com.example.takehome.model

import com.google.gson.annotations.SerializedName


data class UserRepo(
    @SerializedName ("login") var login: String,
    @SerializedName ("id") var id: Int,
    @SerializedName ("avatar_url") var avatar_url: String,
    @SerializedName ("followers") var followers: Int,
    @SerializedName ("following") var following: Int
)

