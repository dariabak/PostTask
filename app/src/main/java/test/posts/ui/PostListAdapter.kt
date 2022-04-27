package test.posts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import test.posts.data.Post
import test.task.R

class PostListAdapter(var items: List<Post>) :
    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    private lateinit var handler: (String) -> Unit

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

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
            handler.invoke(items[position].id.toString())
        }
    }

    override fun getItemCount(): Int = items.size

    fun setUpHandler(handler: (String) -> Unit) {
        this.handler = handler
    }

}