package test.stanton.technicaltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import test.network.ApiError
import test.posts.business.PostListState
import test.posts.business.PostListViewModel
import test.posts.data.Post
import test.task.R

@OptIn(ExperimentalCoroutinesApi::class)
class PostListViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    lateinit var sut: PostListViewModel
    lateinit var postListRepoMock: PostListRepoMock


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        postListRepoMock = PostListRepoMock()
        runTest {
            sut = PostListViewModel(postListRepoMock)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadData_CallsRepoGetPosts() {
        // Given
        // Needed because getPostsCalled was called by init
        postListRepoMock.getPostsCalled = false

        // When
        runTest {
            sut.loadData()
        }

        // Then
        assert(postListRepoMock.getPostsCalled)
    }

    @Test
    fun loadData_WhenSuccessful_ChangesStateFromLoadingToLoaded() {
        // Given
        val postToBeReturned = Post()
        postListRepoMock.getPostsReturnValue = Result.success(listOf(postToBeReturned))
        val states = mutableListOf<PostListState>()
        sut.state.observeForever {
            states.add(it)
        }

        // When
        runTest {
            sut.loadData()
        }

        // Then
        // 0 is Loaded because of init
        assert(states[0] is PostListState.Loaded)
        assertEquals(states[1], PostListState.Loading(R.string.posts_loading_message))
        assertEquals(states[2], PostListState.Loaded(listOf(postToBeReturned)))
    }

    @Test
    fun loadData_WhenFailure_ChangeStateFromLoadingToError() {
        // Given
        postListRepoMock.getPostsReturnValue = Result.failure(ApiError.Generic)
        val states = mutableListOf<PostListState>()
        sut.state.observeForever {
            states.add(it)
        }

        // When
        runTest {
            sut.loadData()
        }

        // Then
        assert(states[0] is PostListState.Loaded)
        assertEquals(states[1], PostListState.Loading(R.string.posts_loading_message))
        assertEquals(states[2], PostListState.Error(R.string.posts_error_message))
    }

}