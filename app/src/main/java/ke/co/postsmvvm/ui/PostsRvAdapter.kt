package ke.co.postsmvvm.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ke.co.postsmvvm.R
import ke.co.postsmvvm.models.Post
import kotlinx.android.synthetic.main.row_post_item.view.*

class PostsRvAdapter(var postsList: List<Post>) :
    RecyclerView.Adapter<PostsRvAdapter.PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        var itemview =
            LayoutInflater.from(parent.context).inflate(R.layout.row_post_item, parent, false)
        return PostsViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        var post = postsList[position]
        holder.rowView.tvTitle.text = post.title
        holder.rowView.tvBody.text = post.body
        holder.rowView.tvUserId.text = post.userId.toString()
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    class PostsViewHolder(val rowView: View) : RecyclerView.ViewHolder(rowView)
}