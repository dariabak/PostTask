package space.posts.data

import javax.inject.Inject

interface PostListRepo {
    suspend fun getPosts(): Result<List<Post>>
}
class PostListRepoImpl @Inject constructor(postsApi: PostsApi): PostListRepo {
    override suspend fun getPosts(): Result<List<Post>> {
        TODO("Not yet implemented")
    }
}