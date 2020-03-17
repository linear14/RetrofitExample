package com.dongldh.retrofitexample.retrofit2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Lesson2. 자바의 Object 값을 JSON으로 넘기기 (POST, PUT)
interface UserClient {

    // createAccount endpoint (HTTP API)
    // User 객체로 들어온 정보로 request -> 그에 따른 response : User 객체 형태로 받음

    // Retrofit 특징: request로 요청할 object의 property 중 null이 있으면, 그 정보를 무시한다.
    // 최초, request 보낼 때 id 값은 null 이기 때문에, 무시하고 나머지 데이터를 가지고 response를 요구한다.
    @POST("user")
    fun createAccount(@Body user: User): Call<User>
}