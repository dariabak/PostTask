package test.savedPosts.business

import test.posts.data.Post

sealed class SavedPostsState {
    data class Loading(val stringRes: Int) : SavedPostsState()
    data class Error(val stringRes: Int) : SavedPostsState()
    data class Loaded(val posts: List<Post>) : SavedPostsState()
}