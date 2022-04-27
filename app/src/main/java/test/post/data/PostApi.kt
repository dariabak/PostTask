package test.post.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import test.posts.data.Post

interface PostApi {
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: String) : Response<Post>
}