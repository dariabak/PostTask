package test.post.data

import test.posts.data.Post

interface PostRepo {
    suspend fun getPost(): Result<Post>
}
class PostRepoImpl: PostRepo {
    override suspend fun getPost(): Result<Post> {
        TODO("Not yet implemented")
    }
}