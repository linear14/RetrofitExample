package com.dongldh.retrofitexample.retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dongldh.retrofitexample.R
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Main2Activity : AppCompatActivity() {

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
        val builder = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/api/")
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.build()

        // request 진행
        val client = retrofit.create(UserClient::class.java)
        val call = client.createAccount(user)

        call.enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@Main2Activity, "something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                Toast.makeText(this@Main2Activity, "UserID ${response.body()?.id}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
