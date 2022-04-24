package test.post.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import test.post.business.PostViewModel
import test.task.databinding.PostLayoutBinding

class PostFragment: Fragment() {
 private lateinit var binding: PostLayoutBinding
 private val postViewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }
}