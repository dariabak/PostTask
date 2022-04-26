package test.saved.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import test.task.databinding.PostLayoutBinding

class SavedPostFragment: Fragment() {
    lateinit var binding: PostLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }
}