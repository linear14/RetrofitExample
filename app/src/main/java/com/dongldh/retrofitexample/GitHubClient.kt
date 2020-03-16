package com.dongldh.retrofitexample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/* 동기 (sync). --> 요청 응답 진행에 따라 프리징

interface GitHubClient {
    @GET("/users/{user}/repos")
    fun reposForUser(@Path("user") user: String): List<GitHubClient>
}

*/

// 비동기 (async). by using Call
interface GitHubClient {
    @GET("/users/{user}/repos")
    fun reposForUser(@Path("user") user: String): Call<List<GitHubRepo>>
}