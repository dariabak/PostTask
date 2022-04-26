package test.saved.business

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.posts.data.Post
import test.saved.data.SavedPostsRepo
import test.task.R
import javax.inject.Inject

@HiltViewModel
class SavedPostsViewModel @Inject constructor(private val repo: SavedPostsRepo): ViewModel() {
    private val _savedPosts = MutableLiveData<List<Post>>()
    val savedPosts: LiveData<List<Post>>
        get() = _savedPosts

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _savedPosts.value = repo.getSavedPosts()
        }
    }
}