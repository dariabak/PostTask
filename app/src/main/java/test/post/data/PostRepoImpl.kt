package test.post.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import test.network.ApiError
import test.posts.data.Post
import test.posts.data.PostsApi
import javax.inject.Inject

interface PostRepo {
    suspend fun getPost(id: String): Result<List<Post>>
}
class PostRepoImpl @Inject constructor(private val postsApi: PostsApi): PostRepo {
    override suspend fun getPost(id: String): Result<List<Post>> {
        val response = postsApi.getPost(id)
        val post = response.body()
        return if (response.isSuccessful && post != null) {
            Result.success(post)
        } else {
            Result.failure(ApiError.Generic)
        }
    }
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class PostRepoModule {
    @Binds
    abstract fun getPostRepo(repo: PostRepoImpl): PostRepo
}