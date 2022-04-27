package test.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.savedPosts.data.SavedPostsRepo
import javax.inject.Inject

@HiltViewModel
class ViewPagerViewModel @Inject constructor(private val repo: SavedPostsRepo) : ViewModel() {

    var numberOfSavedPosts = 0

    fun getNumberOfSavedPosts() {
        viewModelScope.launch {
            numberOfSavedPosts = repo.getNumberOfSavedPosts()
        }
    }
}