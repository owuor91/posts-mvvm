package ke.co.postsmvvm.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ke.co.postsmvvm.R
import ke.co.postsmvvm.repository.PostsRepository
import ke.co.postsmvvm.viewmodel.PostsViewModel
import ke.co.postsmvvm.viewmodel.PostsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var postsViewModel: PostsViewModel
    lateinit var postsViewModelFactory: PostsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postsViewModelFactory = PostsViewModelFactory(PostsRepository())
        postsViewModel =
            ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)
        postsViewModel.getPosts()
        postsViewModel.postsLiveData.observe(this, Observer { posts ->
            val postsAdapter = PostsRvAdapter(posts)
            rvPosts.apply {
                layoutManager = LinearLayoutManager(baseContext)
                adapter = postsAdapter
            }
        })

        postsViewModel.postsFailedLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })

    }
}