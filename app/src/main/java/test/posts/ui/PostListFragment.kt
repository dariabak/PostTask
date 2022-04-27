package test.posts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.error_layout.view.*
import test.posts.business.PostListState
import test.posts.business.PostListViewModel
import test.task.ViewPagerFragment
import test.task.ViewPagerFragmentDirections
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
        adapter = PostListAdapter(listOf())
        binding.postListView.adapter = adapter

        adapter.setUpHandler {
            var action = ViewPagerFragmentDirections.actionViewPagerFragmentToPostFragment(it)
            this.findNavController().navigate(action)
        }
        postsViewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is PostListState.Loaded -> {
                    adapter.items = it.posts

                    binding.postListView.visibility = View.VISIBLE
                    binding.loadingView.loading.visibility = View.GONE
                    binding.errorView.errorLayout.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
                is PostListState.Loading -> {
                    binding.postListView.visibility = View.GONE
                    binding.loadingView.loading.visibility = View.VISIBLE
                    binding.errorView.errorLayout.visibility = View.GONE

                }
                is PostListState.Error -> {
                    binding.postListView.visibility = View.GONE
                    binding.loadingView.loading.visibility = View.GONE
                    binding.errorView.errorLayout.visibility = View.VISIBLE

                    binding.errorView.errorLayout.error_message.text = resources.getString(it.stringRes)

                    binding.errorView.errorLayout.retry_button.setOnClickListener {
                        postsViewModel.loadData()
                    }
                }
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "All posts"
    }
}