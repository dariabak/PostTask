package test.post.business

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.post.data.PostRepo
import test.task.R
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repo: PostRepo): ViewModel() {
    private val _state = MutableLiveData<PostState>()
    val state: LiveData<PostState>
        get() = _state

    init {
        _state.value = PostState.Loading(R.string.posts_loading_message)
    }
    fun loadPost(id: Int) {
        viewModelScope.launch {
            _state.value = PostState.Loading(R.string.posts_loading_message)
            val result = repo.getPost()
            val posts = result.getOrNull()
            val exception = result.exceptionOrNull()
            when {
                posts != null -> _state.value = PostState.Loaded(posts)
                exception != null -> _state.value =
                    PostState.Error(R.string.posts_error_message)
                else -> _state.value = PostState.Error(R.string.posts_error_message)
            }

        }
    }
}