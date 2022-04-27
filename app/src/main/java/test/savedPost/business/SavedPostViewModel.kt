package test.savedPost.business

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.posts.data.Post
import test.savedPosts.data.SavedPostsRepo
import javax.inject.Inject

@HiltViewModel
class SavedPostViewModel @Inject constructor(private val repo: SavedPostsRepo) : ViewModel() {
    private val _savedPost = MutableLiveData<Post>()
    val savedPost: LiveData<Post>
        get() = _savedPost

    fun getSavedPost(id: Int) {
        viewModelScope.launch {
            _savedPost.value = repo.getSavedPost(id)
        }
    }
}