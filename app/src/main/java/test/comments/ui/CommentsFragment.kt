package test.comments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import test.comments.business.CommentsViewModel
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
        binding.commentsView.layoutManager = linearLayoutManager
        val postId = CommentsFragmentArgs.fromBundle(requireArguments()).postId
        commentsViewModel.loadComments(postId)

        return binding.root
    }
}