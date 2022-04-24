package test.posts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import test.posts.data.Post
import test.task.R

class PostListAdapter(var items: List<Post>) :
    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    class PostViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.title).text = items[position].title
        holder.itemView.findViewById<TextView>(R.id.content).text =
            items[position].body
        holder.itemView.setOnClickListener {
//            onItemClick.invoke(items[position].id.toString())
        }
    }

    override fun getItemCount(): Int = items.size


}