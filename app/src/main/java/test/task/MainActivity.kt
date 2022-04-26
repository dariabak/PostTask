package test.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
//        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
//
//        viewPager.adapter = FragmentAdapter(this)
//
//        TabLayoutMediator(tabLayout, viewPager){tab, position ->
//            tab.text = "Tab $position"
//        }.attach()


//        //Initialize the bottom navigation view
//        //create bottom navigation view object
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController: NavController = navHostFragment.navController
//        bottomNavigationView.setupWithNavController(navController)
    }

}