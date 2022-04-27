package test.stanton.technicaltest

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import junit.framework.Assert.assertEquals
import okhttp3.ResponseBody.Companion.toResponseBody
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

    @Before
    fun setUp() {
        postsApiMock = PostsApiMock()
        runTest {
            sut = PostListRepoImpl(postsApiMock)
        }
    }

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