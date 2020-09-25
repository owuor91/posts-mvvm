package ke.co.postsmvvm.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ke.co.postsmvvm.R
import ke.co.postsmvvm.repository.CommentsRepository
import ke.co.postsmvvm.repository.PostsRepository
import ke.co.postsmvvm.viewmodel.CommentsViewModel
import ke.co.postsmvvm.viewmodel.CommentsViewModelFactory
import ke.co.postsmvvm.viewmodel.PostsViewModel
import ke.co.postsmvvm.viewmodel.PostsViewModelFactory
import kotlinx.android.synthetic.main.activity_comments.*

class CommentsActivity : AppCompatActivity() {
    var postId: Int = 0
    lateinit var postsViewModel: PostsViewModel
    lateinit var postsViewModelFactory: PostsViewModelFactory
    lateinit var commentsViewModel: CommentsViewModel
    lateinit var commentsViewModelFactory: CommentsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        postId = intent.getIntExtra("POST_ID", 0)

        postsViewModelFactory = PostsViewModelFactory(PostsRepository())
        postsViewModel =
            ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)

        postsViewModel.getPostById(postId)

        commentsViewModelFactory = CommentsViewModelFactory(CommentsRepository())
        commentsViewModel =
            ViewModelProvider(this, commentsViewModelFactory).get(CommentsViewModel::class.java)

        commentsViewModel.getComments(postId)
    }

    override fun onResume() {
        super.onResume()
        postsViewModel.postByIdLiveData.observe(this, Observer { post ->
            tvTitle.text = post.title
            tvBody.text = post.body
        })

        commentsViewModel.commentsLiveData.observe(this, Observer { commentsList ->
            val commentsAdapter = CommentsAdapter(commentsList)
            rvComments.layoutManager = LinearLayoutManager(this)
            rvComments.adapter = commentsAdapter
        })

        commentsViewModel.commentsFailedLiveData.observe(this, Observer { errorMessage ->
            Toast.makeText(baseContext, errorMessage, Toast.LENGTH_SHORT).show()
        })

    }
}