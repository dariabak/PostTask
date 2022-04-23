package space.posts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import space.stanton.technicaltest.R
import space.stanton.technicaltest.databinding.PostListFragmentLayoutBinding

class PostListFragment: Fragment() {
    private lateinit var binding: PostListFragmentLayoutBinding

    override fun onCreate(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.post_list_fragment_layout,
            container,
            false
        )


        return binding.root
    }
}