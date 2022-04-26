package test.saved.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import test.posts.data.Post
import test.saved.business.SavedPostsState
import test.saved.business.SavedPostsViewModel
import test.saved.components.Loading
import test.saved.components.Error
import test.task.R


@AndroidEntryPoint
class SavedPostFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        ComposeView(requireContext()).apply {
            setContent {
                SavedPostScreen()
            }
    }
}

@Composable
fun SavedPostScreen(
    savedPostsViewModel: SavedPostsViewModel = hiltViewModel()
) {
    val savedPosts by savedPostsViewModel.savedPosts.observeAsState(initial = emptyList())
    SavedPostList(posts = savedPosts)
}

@Composable
fun SavedPostList(
    posts: List<Post>
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(10.dp)
    ) {
        val listState = rememberLazyListState()
        LazyColumn(state = listState) {
            items(posts) { post ->
                SinglePost(post = post)
            }
        }
    }

}
@Composable
fun SinglePost(post: Post) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 120.dp)
            .padding(0.dp, 8.dp, 8.dp, 8.dp),

    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = post.title,
                style = TextStyle(fontSize = 23.sp, color = Color.Gray),
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
            )
            Text(text = post.body,
                style = TextStyle(color = Color.Gray),
                maxLines = 2,
                textAlign = TextAlign.Start
            )
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
            )

        }
    }
}