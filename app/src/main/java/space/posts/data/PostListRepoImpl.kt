package space.posts.data

interface PostListRepo {
    suspend fun getPosts(): Result<List<Post>>
}
class PostListRepoImpl {
}