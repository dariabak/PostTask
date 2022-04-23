package space.posts.business

import space.posts.data.Post

sealed class PostListState {
    data class Loading(val stringRes: Int): PostListState()
    data class Error(val stringRes: Int): PostListState()
    data class Loaded(val posts: List<Post>): PostListState()
}