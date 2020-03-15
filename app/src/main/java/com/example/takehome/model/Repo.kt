package com.example.takehome.model

import com.google.gson.annotations.SerializedName

data class Repo (

    @SerializedName("name") var name: String,
    @SerializedName ("description") var descripton: String,
    @SerializedName ("updated_at") var updated_at: String,
    @SerializedName ("stargazers_count") var stargazers_count: String,
    @SerializedName ("forks_count") var forks_count: String)

