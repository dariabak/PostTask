package test.comments.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import test.network.ApiError

import test.posts.data.PostsApi
import javax.inject.Inject

interface CommentsRepo {
    suspend fun getComments(postId: String): Result<List<Comment>>
}
class CommentsRepoImpl @Inject constructor(private val postCommentsApi: PostCommentsApi): CommentsRepo {
    override suspend fun getComments(postId: String): Result<List<Comment>> {
            val response = postCommentsApi.getComments(postId)
            val comments = response.body()
            return if (response.isSuccessful && comments != null) {
                Result.success(comments)
            } else {
                Result.failure(ApiError.Generic)
            }
    }
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class CommentsRepoModule {
    @Binds
    abstract fun getCommentsRepo(repo: CommentsRepoImpl): CommentsRepo
}