package space.posts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import space.stanton.technicaltest.R

class PostListAdapter(val items: MutableList<JSONObject>, val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    class PostViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.title).text = items[position].getString("title")
        holder.itemView.findViewById<TextView>(R.id.content).text =
            items[position].getString("body")
        holder.itemView.setOnClickListener {
            onItemClick(items[position].getString("id"))
        }
    }

    override fun getItemCount(): Int = items.size

}