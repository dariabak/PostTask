package space.posts.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {
    @GET("posts")
    suspend fun getPosts() : Response<List<Post>>
}