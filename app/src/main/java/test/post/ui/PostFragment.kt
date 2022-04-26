package test.post.ui

import android.R
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import test.post.business.PostState
import test.post.business.PostViewModel
import test.task.databinding.PostLayoutBinding


@AndroidEntryPoint
class PostFragment: Fragment() {
 private lateinit var binding: PostLayoutBinding
 private val postViewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostLayoutBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        binding.lifecycleOwner = this
        val postId = PostFragmentArgs.fromBundle(requireArguments()).postId
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Post $postId"
        postViewModel.loadPost(postId)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        postViewModel.isSaved.observe(viewLifecycleOwner) {
            if(it) {
                binding.saveButton.text = "Saved"
                binding.saveButton.isClickable = false
                binding.saveButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.darker_gray)
            }
        }

        postViewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is PostState.Loaded -> {

                    binding.post.visibility = View.VISIBLE
                    binding.loadingView.loading.visibility = View.GONE
                    binding.errorView.errorMessage.visibility = View.GONE

                    binding.title.text = it.post.title
                    binding.body.text = it.post.body

                }
                is PostState.Loading -> {
                    binding.post.visibility = View.GONE
                    binding.loadingView.loading.visibility = View.VISIBLE
                    binding.errorView.errorMessage.visibility = View.GONE
                }
                is PostState.Error -> {
                    binding.post.visibility = View.GONE
                    binding.loadingView.loading.visibility = View.GONE
                    binding.errorView.errorMessage.visibility = View.VISIBLE
                }
            }
        }

        binding.commentsButton.setOnClickListener {
            findNavController().navigate(PostFragmentDirections.actionPostFragmentToCommentsFragment(postId))
        }

        binding.saveButton.setOnClickListener {
            postViewModel.savePost()
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