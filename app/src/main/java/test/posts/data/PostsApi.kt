package test.posts.data

import test.comments.data.Comment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApi {
    @GET("posts")
    suspend fun getPosts() : Response<List<Post>>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: String) : Response<Post>

    @GET("posts/{postId}/comments")
    suspend fun getComments(@Path("postId") id: String) : Response<List<Comment>>
}