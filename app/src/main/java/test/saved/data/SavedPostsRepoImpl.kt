package test.saved.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import test.post.data.PostLocalStore
import test.posts.data.Post
import javax.inject.Inject


interface SavedPostsRepo {
    suspend fun getSavedPosts(): List<Post>
}
class SavedPostsRepoImpl @Inject constructor(private val localStore: PostLocalStore): SavedPostsRepo {

    override suspend fun getSavedPosts(): List<Post> {
        return localStore.getListOfSavedPosts()
    }
}


@InstallIn(ViewModelComponent::class)
@Module
abstract class SavedPostsRepoModule {
    @Binds
    abstract fun getSavedPostsRepo(repo: SavedPostsRepoImpl): SavedPostsRepo
}