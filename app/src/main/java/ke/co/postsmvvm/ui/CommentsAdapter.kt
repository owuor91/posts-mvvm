package ke.co.postsmvvm.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ke.co.postsmvvm.R
import ke.co.postsmvvm.models.Comment
import kotlinx.android.synthetic.main.row_comment_item.view.*

class CommentsAdapter(var commentsList: List<Comment>) :
    RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        var itemview =
            LayoutInflater.from(parent.context).inflate(R.layout.row_comment_item, parent, false)
        return CommentsViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var comment = commentsList.get(position)
        holder.rowView.tvName.text = comment.name
        holder.rowView.tvCommentBody.text = comment.body
        holder.rowView.tvEmail.text = comment.email
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }

    class CommentsViewHolder(val rowView: View) : RecyclerView.ViewHolder(rowView)
}