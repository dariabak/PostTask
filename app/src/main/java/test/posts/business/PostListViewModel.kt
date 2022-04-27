package test.posts.business

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.posts.data.PostListRepo
import test.task.R
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(private val repo: PostListRepo) : ViewModel() {

    private val _state = MutableLiveData<PostListState>()
    val state: LiveData<PostListState>
        get() = _state

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _state.value = PostListState.Loading(R.string.posts_loading_message)
            val result = repo.getPosts()
            val posts = result.getOrNull()
            val exception = result.exceptionOrNull()
            when {
                posts != null -> _state.value = PostListState.Loaded(posts)
                exception != null -> _state.value =
                    PostListState.Error(R.string.posts_error_message)
                else -> _state.value = PostListState.Error(R.string.generic_error_message)
            }

        }
    }
}