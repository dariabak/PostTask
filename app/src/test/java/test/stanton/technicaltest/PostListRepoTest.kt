package test.stanton.technicaltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import junit.framework.Assert.assertEquals
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import test.network.ApiError
import test.posts.data.Post
import test.posts.data.PostListRepo
import test.posts.data.PostListRepoImpl

@OptIn(ExperimentalCoroutinesApi::class)
class PostListRepoTest {
    lateinit var sut: PostListRepo
    lateinit var postsApiMock: PostsApiMock
//    private val dispatcher = StandardTestDispatcher()
//
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
//        Dispatchers.setMain(dispatcher)
        postsApiMock = PostsApiMock()
        runTest {
            sut = PostListRepoImpl(postsApiMock)
        }
    }

//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//    }

    @Test
    fun repoGetPosts_CallsPostsApi() {
        // When
        runTest {
            sut.getPosts()
        }

        // Then
        assert(postsApiMock.getPostsCalled)
    }

    @Test
    fun repoGetPosts_WhenPostsApiReturnsSuccessResponse_ReturnsSuccessResult() {
        // Given
        val postToBeReturned = Post()
        postsApiMock.getPostsResultValue = Response.success(listOf(postToBeReturned))
        var result: Result<List<Post>>? = null

        // When
        runTest {
            result = sut.getPosts()
        }

        // Then
        assertEquals(result, Result.success(listOf(postToBeReturned)))
    }

    @Test
    fun repoGetPosts_WhenPostsApiReturnsErrorResponse_ReturnsFailureResult() {
        // Given
        postsApiMock.getPostsResultValue = Response.error(501, byteArrayOf().toResponseBody())
        var result: Result<List<Post>>? = null

        // When
        runTest {
            result = sut.getPosts()
        }

        // Then
        assertEquals(result, Result.failure<ApiError>(ApiError.Generic))
    }
}