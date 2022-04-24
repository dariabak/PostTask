package test.posts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import test.posts.business.PostListViewModel
import test.task.databinding.PostListFragmentLayoutBinding

@AndroidEntryPoint
class PostListFragment: Fragment() {
    private lateinit var binding: PostListFragmentLayoutBinding
    private lateinit var postsViewModel: PostListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostListFragmentLayoutBinding.inflate(inflater, container, false)
        postsViewModel = ViewModelProvider(this)[PostListViewModel::class.java]

        return binding.root
    }
}