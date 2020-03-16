package com.dongldh.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Retrofit의 핵심 클래스는 Retrofit.
        //    이를 조금 더 유연하게 사용하기 위한 Retrofit.Builder 사용.
        // 2. github는 데이터 제공을 JSON으로 해줌.
        //    따라서, Java object와 JSON을 상호 convert 하기 위해, GSON 사용.
        val builder = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())

        // Builder를 다 사용했으므로, 실제로 Retrofit 객체를 만든다.
        val retrofit: Retrofit = builder.build()

        // 실제 request를 진행
        val client: GitHubClient = retrofit.create(GitHubClient::class.java)
        val call: Call<List<GitHubRepo>> = client.reposForUser("linear14")

        // Call Object 사용 (비동기)
        // callback method는 server로부터 response를 받을 때 호출
        call.enqueue(object: Callback<List<GitHubRepo>>{
            override fun onFailure(call: Call<List<GitHubRepo>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "error :(", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<GitHubRepo>>, response: Response<List<GitHubRepo>>) {
                // body() --> List<GitHubRepo>
                val repos = response.body()!!

                listView.adapter = GitHubRepoAdapter(this@MainActivity, repos)
            }

        })

    }
}
