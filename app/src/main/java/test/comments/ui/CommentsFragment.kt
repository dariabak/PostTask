package test.comments.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.error_layout.view.*
import test.comments.business.CommentsState
import test.comments.business.CommentsViewModel
import test.posts.business.PostListState
import test.posts.ui.PostListAdapter
import test.task.databinding.CommentsFragmentLayoutBinding

@AndroidEntryPoint
class CommentsFragment: Fragment() {
    private lateinit var binding: CommentsFragmentLayoutBinding
    private val commentsViewModel: CommentsViewModel by viewModels()
    private lateinit var adapter: CommentsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CommentsFragmentLayoutBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        linearLayoutManager = LinearLayoutManager(requireActivity())
        setHasOptionsMenu(true)
        binding.commentsView.layoutManager = linearLayoutManager
        val postId = CommentsFragmentArgs.fromBundle(requireArguments()).postId
        commentsViewModel.loadComments(postId)
        adapter = CommentsAdapter(listOf())
        binding.commentsView.adapter = adapter
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Comments - post $postId"
        commentsViewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is CommentsState.Loaded -> {
                    adapter.items = it.comments

                    binding.commentsView.visibility = View.VISIBLE
                    binding.loadingView.loading.visibility = View.GONE
                    binding.errorView.errorLayout.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
                is CommentsState.Loading -> {
                    binding.commentsView.visibility = View.GONE
                    binding.loadingView.loading.visibility = View.VISIBLE
                    binding.errorView.errorLayout.visibility = View.GONE

                }
                is CommentsState.Error -> {
                    binding.commentsView.visibility = View.GONE
                    binding.loadingView.loading.visibility = View.GONE
                    binding.errorView.errorLayout.visibility = View.VISIBLE

                    binding.errorView.errorLayout.error_message.text = resources.getString(it.stringRes)

                    binding.errorView.errorLayout.retry_button.setOnClickListener {
                        commentsViewModel.loadComments(postId)
                    }
                }
            }
        }
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                findNavController().navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}