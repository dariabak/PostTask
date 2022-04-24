package test.comments.business

import test.comments.data.Comment

sealed class CommentsState {
    data class Loading(val stringRes: Int): CommentsState()
    data class Error(val stringRes: Int): CommentsState()
    data class Loaded(val comments: List<Comment>): CommentsState()
}