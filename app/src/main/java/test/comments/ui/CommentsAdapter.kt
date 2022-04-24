package test.comments.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import test.comments.data.Comment
import test.task.R

class CommentsAdapter(var items: List<Comment>):
    RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    class CommentsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsAdapter.CommentsViewHolder {
        return CommentsAdapter.CommentsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.comment_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommentsAdapter.CommentsViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.name).text = items[position].name
        holder.itemView.findViewById<TextView>(R.id.email).text = items[position].email
        holder.itemView.findViewById<TextView>(R.id.body).text =
            items[position].body

    }

    override fun getItemCount(): Int = items.size


}