package space.stanton.technicaltest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import space.network.ApiCalls
import space.post.PostDetailActivity
import space.posts.ui.PostListAdapter


/**
 * Displays a list of posts
 */
class PostListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Thread {
//            ApiCalls.loadAll {
//                if (it.second != null) {
//                    //TODO - handle error
//                } else {
//                    var json = JSONArray(it.first!!.string())
//                    runOnUiThread {
//                        var listOfPosts = mutableListOf<JSONObject>()
//                        for (i in 0 until json.length()) {
//                            listOfPosts.add(i, json.getJSONObject(i))
//                        }
//                        findViewById<RecyclerView>(R.id.postsList).adapter =
//                            PostListAdapter(listOfPosts, onItemClick = { id ->
//                                startActivity(
//                                    Intent(this, PostDetailActivity::class.java)
//                                        .putExtra("postId", id)
//                                )
//                            })
//                    }
//                }
//            }
//        }.start()

    }
}

