package test.saved.business

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.saved.data.SavedPostsRepo
import test.task.R
import javax.inject.Inject

@HiltViewModel
class SavedPostsViewModel @Inject constructor(private val repo: SavedPostsRepo): ViewModel() {
    private val _state = MutableLiveData<SavedPostsState>()
    val state: LiveData<SavedPostsState>
        get() = _state

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _state.value =  SavedPostsState.Loading(R.string.posts_loading_message)
            val savedPosts = repo.getSavedPosts()

            _state.value = SavedPostsState.Loaded(savedPosts)
        }
    }
}