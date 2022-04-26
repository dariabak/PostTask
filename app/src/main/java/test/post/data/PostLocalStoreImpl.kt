package test.post.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import test.posts.data.Post
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

interface PostLocalStore {
    fun savePost(post: Post)
    fun checkIfPostSaved(id: Int): Boolean
    fun getListOfSavedPosts(): List<Post>
    fun getNumberOfSavedPosts(): Int
    fun getSavedPost(id: Int): Post
}
class PostLocalStoreImpl (private val context: Context): PostLocalStore {
    private val fileName = "SavedPostList"
    private fun getJsonArray(): ArrayList<Post> {
        var json = ""
        val gson = Gson()
        val file = File(context.filesDir, fileName)
        if (file.exists()) {
            val inputStream = context.openFileInput(fileName)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString: String? = ""
            val stringBuilder = StringBuilder()
            while (bufferedReader.readLine().also { receiveString = it } != null) {
                stringBuilder.append(receiveString)
            }
            json = stringBuilder.toString()
            if(json == ""){
                return ArrayList<Post>()
            } else {
                val postType = object : TypeToken<ArrayList<Post>>() {}.type
                return gson.fromJson(json, postType)
            }
        } else {
            return ArrayList<Post>()
        }
    }

    override fun savePost(post: Post) {
        val gson = Gson()
        var postArrayList: ArrayList<Post> = getJsonArray()
        if(!checkIfPostSaved(post.id)) {
            postArrayList.add(post)
        }
        var jsonString = gson.toJson(postArrayList)
        val fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        fileOutputStream.write(jsonString.toByteArray())

    }

    override fun checkIfPostSaved(id: Int): Boolean {
        var isPostSaved = false
        var postArrayList: ArrayList<Post> = getJsonArray()
        if(postArrayList.isNotEmpty()){
            var posts = postArrayList.filter{ it.id == id}.toCollection(ArrayList())
            if(posts.isNotEmpty()){
                isPostSaved = true
            }
        }
        return isPostSaved
    }

    override fun getListOfSavedPosts(): List<Post> {
        return getJsonArray().toList()
    }

    override fun getNumberOfSavedPosts(): Int {
        var posts = getJsonArray()
        return posts.size
    }

    override fun getSavedPost(id: Int): Post {
        var posts = getJsonArray()
        var arrayList = posts.filter { it.id == id}
        return arrayList.first()
    }
}



@InstallIn(SingletonComponent::class)
@Module
object LocalStorageModule {

    @Singleton
    @Provides
    fun getLocalStorage(@ApplicationContext appContext: Context): PostLocalStore = PostLocalStoreImpl(appContext)
}