package test.comments.business

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.comments.data.CommentsRepo
import test.task.R
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(private val repo: CommentsRepo) : ViewModel() {

    private val _state = MutableLiveData<CommentsState>()
    val state: LiveData<CommentsState>
        get() = _state


    fun loadComments(id: String) {
        viewModelScope.launch {
            _state.value = CommentsState.Loading(R.string.posts_loading_message)
            val result = repo.getComments(id)
            val comments = result.getOrNull()
            val exception = result.exceptionOrNull()
            when {
                comments != null -> _state.value = CommentsState.Loaded(comments)
                exception != null -> _state.value =
                    CommentsState.Error(R.string.posts_error_message)
                else -> _state.value = CommentsState.Error(R.string.generic_error_message)
            }

        }
    }
}