package test.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import test.comments.data.PostCommentsApi
import test.post.data.PostApi
import test.posts.data.PostsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providesPostsApi(retrofit: Retrofit): PostsApi =
        retrofit.create(PostsApi::class.java)

    @Singleton
    @Provides
    fun providesPostApi(retrofit: Retrofit): PostApi =
        retrofit.create(PostApi::class.java)

    @Singleton
    @Provides
    fun providesPostCommentsApi(retrofit: Retrofit): PostCommentsApi =
        retrofit.create(PostCommentsApi::class.java)

}