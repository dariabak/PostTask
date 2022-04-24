package test.post.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
        binding.lifecycleOwner = this
        val args = PostFragmentArgs.fromBundle(requireArguments())
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Post ${args.postId}"
        postViewModel.loadPost(args.postId)

        postViewModel.state.observe(viewLifecycleOwner) {
            when(it) {
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

        return binding.root
    }
}