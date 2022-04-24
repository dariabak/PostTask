package test.post.business

import test.posts.data.Post

sealed class PostState {
    data class Loading(val stringRes: Int): PostState()
    data class Error(val stringRes: Int): PostState()
    data class Loaded(val posts: Post): PostState()
}
