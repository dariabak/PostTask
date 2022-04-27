package test.post.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import test.network.ApiError
import test.posts.data.Post
import javax.inject.Inject

interface PostRepo {
    suspend fun getPost(id: String): Result<Post>
    fun savePost(post: Post)
    fun checkIfPostSaved(id: String): Boolean
}

class PostRepoImpl @Inject constructor(
    private val postApi: PostApi,
    private val postLocalStore: PostLocalStore
) : PostRepo {
    override suspend fun getPost(id: String): Result<Post> {
        val response = postApi.getPost(id)
        val post = response.body()
        return if (response.isSuccessful && post != null) {
            Result.success(post)
        } else {
            Result.failure(ApiError.Generic)
        }
    }

    override fun savePost(post: Post) {
        postLocalStore.savePost(post)
    }

    override fun checkIfPostSaved(id: String): Boolean {
        return postLocalStore.checkIfPostSaved(id.toInt())
    }
}


@InstallIn(ViewModelComponent::class)
@Module
abstract class PostRepoModule {
    @Binds
    abstract fun getPostRepo(repo: PostRepoImpl): PostRepo
}