package test.posts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import test.posts.business.PostListState
import test.posts.business.PostListViewModel
import test.task.databinding.PostListFragmentLayoutBinding

@AndroidEntryPoint
class PostListFragment: Fragment() {
    private lateinit var binding: PostListFragmentLayoutBinding
    private val postsViewModel: PostListViewModel by viewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: PostListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostListFragmentLayoutBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        linearLayoutManager = LinearLayoutManager(requireActivity())
        binding.postListView.layoutManager = linearLayoutManager

//        setUpHandler { id ->
//
//        }
        adapter = PostListAdapter(listOf())

        binding.postListView.adapter = adapter

        adapter.setUpHandler {

        }
        postsViewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is PostListState.Loaded -> {
                    adapter.items = it.posts

                    binding.postListView.visibility = View.VISIBLE
                    binding.loadingView.loading.visibility = View.GONE
                    binding.errorView.errorMessage.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
                is PostListState.Loading -> {
                    binding.postListView.visibility = View.GONE
                    binding.loadingView.loading.visibility = View.VISIBLE
                    binding.errorView.errorMessage.visibility = View.GONE

                }
                is PostListState.Error -> {
                    binding.postListView.visibility = View.GONE
                    binding.loadingView.loading.visibility = View.GONE
                    binding.errorView.errorMessage.visibility = View.VISIBLE
                }
            }
        }
        return binding.root
    }

}