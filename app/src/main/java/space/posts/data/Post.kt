package space.posts.data

import com.google.gson.annotations.SerializedName

class Post {
    @SerializedName("title")
    val title: String = ""

    @SerializedName("body")
    val body: String = ""
}