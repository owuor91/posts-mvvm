package ke.co.postsmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ke.co.postsmvvm.R
import ke.co.postsmvvm.repository.PostsRepository
import ke.co.postsmvvm.viewmodel.PostsViewModel
import ke.co.postsmvvm.viewmodel.PostsViewModelFactory
import kotlinx.android.synthetic.main.activity_comments.*

class CommentsActivity : AppCompatActivity() {
    var postId: Int = 0
    lateinit var postsViewModel: PostsViewModel
    lateinit var postsViewModelFactory: PostsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        postId = intent.getIntExtra("POST_ID", 0)

        postsViewModelFactory = PostsViewModelFactory(PostsRepository())
        postsViewModel =
            ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)

        postsViewModel.getPostById(postId)
    }

    override fun onResume() {
        super.onResume()
        postsViewModel.postByIdLiveData.observe(this, Observer { post->
            tvTitle.text = post.title
            tvBody.text = post.body
        })

    }
}