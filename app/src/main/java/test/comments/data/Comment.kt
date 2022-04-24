package test.comments.data

import com.google.gson.annotations.SerializedName

class Comment {
    @SerializedName("postId")
    val postId: Int = -1

    @SerializedName("id")
    val id: Int = -1

    @SerializedName("name")
    val name: String = ""

    @SerializedName("email")
    val email: String = ""

    @SerializedName("body")
    val body: String = ""
}