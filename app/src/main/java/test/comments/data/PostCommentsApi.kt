package test.comments.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostCommentsApi {
    @GET("posts/{postId}/comments")
    suspend fun getComments(@Path("postId") id: String): Response<List<Comment>>
}