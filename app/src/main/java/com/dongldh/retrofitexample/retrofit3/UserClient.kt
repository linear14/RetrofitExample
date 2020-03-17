package com.dongldh.retrofitexample.retrofit3

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Lesson3. Logging request and response by OkHttp
// 도입이유 : API를 사용하기 때문에, 실제 어떻게 데이터가 전송되고 응답되는지 알 수 없음. 따라서 오류 발생할 시 Logging 하는 방법이 필요함.
// logging data는 모든 사용자들에게 접근 가능할 수 있음. --> 보안 취약
// 따라서, development mode 에서, 혹은 before release 상태에서만 logging 사용할 것.
// 위의 상황에서만 logging 될 수 있도록 도와주는 방법 --> BuildConfig.DEBUG

// logging의 Level을 전체로 설정하면, 모든 data를 읽으려고 함.
// 따라서 용량이 큰 사진/동영상 등의 것들을 request, response 하면 logging data가 어마어마하게 많아져서
// 전체적인 성능을 떨어뜨릴 수 있음. 그러니깐 적당히 잘 사용하자.
interface UserClient {

    @POST("user")
    fun createAccount(@Body user: User): Call<User>
}