package test.stanton.technicaltest

import test.posts.data.Post
import test.posts.data.PostListRepo

class PostListRepoMock: PostListRepo {
    var getPostsReturnValue: Result<List<Post>> = Result.success(listOf())
    var getPostsCalled = false

    override suspend fun getPosts(): Result<List<Post>> {
        getPostsCalled = true
        return getPostsReturnValue
    }
}