package test.posts.data

import test.comments.data.Comment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsApi {
    @GET("posts")
    suspend fun getPosts() : Response<List<Post>>

    @GET("posts")
    suspend fun getPost(@Query("id") id: String) : Response<List<Post>>

    @GET("posts/{postId}/comments")
    suspend fun getComments(@Path("postId") id: String) : Response<List<Comment>>
}