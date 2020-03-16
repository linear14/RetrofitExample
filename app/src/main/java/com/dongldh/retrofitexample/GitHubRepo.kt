package com.dongldh.retrofitexample

import com.google.gson.annotations.SerializedName

// GitHub Repository에서 제공되는 데이터에 대한 처리
// 간단하게, repository의 name만을 사용해보는 실습.
// getter를 통해, UI 에서 name을 이용할 수 있도록 만든다.

class GitHubRepo {
    @SerializedName("name")
    var name: String? = null
}