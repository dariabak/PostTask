package test.task

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import test.posts.ui.PostListFragment
import test.savedPosts.ui.SavedPostsFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment = PostListFragment()
        if (position == 0) {
            fragment = PostListFragment()
        } else if (position == 1) {
            fragment = SavedPostsFragment()
        }
        return fragment
    }

}
