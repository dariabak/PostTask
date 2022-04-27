package test.savedPosts.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import test.post.data.PostLocalStore
import test.posts.data.Post
import javax.inject.Inject


interface SavedPostsRepo {
    suspend fun getSavedPosts(): List<Post>
    suspend fun getNumberOfSavedPosts(): Int
    suspend fun getSavedPost(id: Int): Post
}

class SavedPostsRepoImpl @Inject constructor(private val localStore: PostLocalStore) :
    SavedPostsRepo {

    override suspend fun getSavedPosts(): List<Post> {
        return localStore.getListOfSavedPosts()
    }

    override suspend fun getNumberOfSavedPosts(): Int {
        return localStore.getNumberOfSavedPosts()
    }

    override suspend fun getSavedPost(id: Int): Post {
        return localStore.getSavedPost(id)
    }
}


@InstallIn(ViewModelComponent::class)
@Module
abstract class SavedPostsRepoModule {
    @Binds
    abstract fun getSavedPostsRepo(repo: SavedPostsRepoImpl): SavedPostsRepo
}