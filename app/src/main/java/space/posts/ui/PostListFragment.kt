package space.posts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import space.stanton.technicaltest.databinding.PostListFragmentLayoutBinding

class PostListFragment: Fragment() {
    private lateinit var binding: PostListFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostListFragmentLayoutBinding.inflate(inflater, container, false)



        return binding.root
    }
}