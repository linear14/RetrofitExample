package com.dongldh.retrofitexample.retrofit1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
// Lesson1. 기본적인 Retrofit 이해와 GET을 통한 request

// 여기서 EndPoint의 설정/조작이 이루어짐 (Describe endpoint here)

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