package ke.co.postsmvvm.ui

import ke.co.postsmvvm.models.Post

interface PostItemClickListener {
    fun onItemClick(post: Post)
}