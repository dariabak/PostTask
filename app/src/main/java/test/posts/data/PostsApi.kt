package test.posts.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {
    @GET("posts")
    suspend fun getPosts() : Response<List<Post>>

    @GET("posts")
    suspend fun getPost(@Query("id") id: Int) : Response<Post>
}