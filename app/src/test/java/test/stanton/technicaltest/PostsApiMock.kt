package test.stanton.technicaltest

import retrofit2.Response
import test.posts.data.Post
import test.posts.data.PostsApi

class PostsApiMock: PostsApi {
    var getPostsResultValue: Response<List<Post>> = Response.success(listOf())
    var getPostsCalled = false

    override suspend fun getPosts(): Response<List<Post>> {
        getPostsCalled = true
        return getPostsResultValue
    }
}