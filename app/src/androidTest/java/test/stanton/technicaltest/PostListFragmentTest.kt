package test.stanton.technicaltest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import test.task.MainActivity

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class PostListFragmentTest {

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
    }

    @Test
    fun testWhenPostIsClicked() {
        // I haven't much time to finish this test
        Thread.sleep(3000)
            onView(withText("qui est esse"))
                .perform(click())
        onView(withText("Save")).check(matches(isDisplayed()))


    }
}