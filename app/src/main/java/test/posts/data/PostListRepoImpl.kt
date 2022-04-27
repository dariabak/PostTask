package test.posts.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import test.network.ApiError
import javax.inject.Inject

interface PostListRepo {
    suspend fun getPosts(): Result<List<Post>>
}

class PostListRepoImpl @Inject constructor(private val postsApi: PostsApi) : PostListRepo {
    override suspend fun getPosts(): Result<List<Post>> {
        val response = postsApi.getPosts()
        val posts = response.body()
        return if (response.isSuccessful && posts != null) {
            Result.success(posts)
        } else {
            Result.failure(ApiError.Generic)
        }
    }
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class PostsRepositoryModule {
    @Binds
    abstract fun getPostsRepo(repo: PostListRepoImpl): PostListRepo
}