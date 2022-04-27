package test.posts.data

import com.google.gson.annotations.SerializedName

class Post {
    @SerializedName("userId")
    val userId: Int = -1

    @SerializedName("id")
    val id: Int = -1

    @SerializedName("title")
    val title: String = ""

    @SerializedName("body")
    val body: String = ""
}