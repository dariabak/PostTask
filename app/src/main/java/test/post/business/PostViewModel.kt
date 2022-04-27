package test.post.business

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.post.data.PostRepo
import test.posts.data.Post
import test.task.R
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repo: PostRepo): ViewModel() {
    private val _state = MutableLiveData<PostState>()
    val state: LiveData<PostState>
        get() = _state

    lateinit var postS: Post
    private val _isSaved = MutableLiveData<Boolean>()
    val isSaved: LiveData<Boolean>
        get() = _isSaved

    init {
        _state.value = PostState.Loading(R.string.posts_loading_message)
    }
    fun loadPost(id: String) {
        viewModelScope.launch {
            _state.value = PostState.Loading(R.string.posts_loading_message)
            checkIfIsSaved(id)
            val result = repo.getPost(id)
            val post = result.getOrNull()
            val exception = result.exceptionOrNull()
            when {
                post != null -> {
                    _state.value = PostState.Loaded(post)
                    postS = post
                }
                exception != null -> _state.value =
                    PostState.Error(R.string.posts_error_message)
                else -> _state.value = PostState.Error(R.string.generic_error_message)
            }

        }
    }

    fun checkIfIsSaved(id: String) {
        _isSaved.value = repo.checkIfPostSaved(id)
    }

    fun savePost() {
        repo.savePost(postS)
        _isSaved.value = repo.checkIfPostSaved(postS.id.toString())
    }
}