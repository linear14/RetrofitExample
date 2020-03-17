package com.dongldh.retrofitexample.retrofit3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dongldh.retrofitexample.BuildConfig
import com.dongldh.retrofitexample.R
import kotlinx.android.synthetic.main.activity_main2.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button_signup.setOnClickListener {
            val user = User(
                input_name.text.toString(),
                input_email.text.toString(),
                input_age.text.toString().toInt(),
                input_topics.text.toString().split(",")
            )
            sendNetworkRequest(user)
        }
    }

    fun sendNetworkRequest(user: User) {
        // create OkHttp client
        val okhttpClientBuilder = OkHttpClient.Builder()


       /*
        create Logging interceptor,  connect with OkHttpClientBuilder
        --------------------------------------------------------------
        얼마나 많은 log 정보를 남길 것인지 4가지 level로 정할 수 있음. (by setLevel())
        Level.NONE -> Nothing, Level.BASIC -> request and response line
        Level.Headers -> basic + respective headers, Level.Body -> everything!
        */
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        if(BuildConfig.DEBUG) {
            okhttpClientBuilder.addInterceptor(logging)
        } // if문 -> 개발 모드에서만 디버깅. (보안 이슈)


        val builder = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClientBuilder.build())

        val retrofit = builder.build()

        // request 진행
        val client = retrofit.create(UserClient::class.java)
        val call = client.createAccount(user)

        call.enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@Main3Activity, "something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                Toast.makeText(this@Main3Activity, "UserID ${response.body()?.id}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
