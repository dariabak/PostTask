package test.savedpost.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import test.posts.data.Post
import test.savedpost.business.SavedPostViewModel

@AndroidEntryPoint
class SavedPostFragment: Fragment() {
    private val viewModel: SavedPostViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val id = SavedPostFragmentArgs.fromBundle(requireArguments()).id
        viewModel.getSavedPost(id)
        setHasOptionsMenu(true)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Post $id"
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return ComposeView(requireContext()).apply {
            setContent {
                SavedPostScreen(viewModel)
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


@Composable
private fun SavedPostScreen(
    savedPostViewModel: SavedPostViewModel,
) {
    val savedPost by savedPostViewModel.savedPost.observeAsState()
    savedPost?.let { SavedPost(post = it) }
}

@Composable
private fun SavedPost(post: Post) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp, 8.dp, 8.dp, 8.dp),
        contentAlignment = Alignment.Center,


    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),

            ) {
            Text(
                text = post.title,
                style = TextStyle(fontSize = 23.sp, color = Color.Gray),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
            )
            Text(
                text = post.body,
                style = TextStyle(color = Color.Gray),
                maxLines = 8,
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
