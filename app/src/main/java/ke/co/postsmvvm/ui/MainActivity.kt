package ke.co.postsmvvm.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ke.co.postsmvvm.R
import ke.co.postsmvvm.models.Post
import ke.co.postsmvvm.repository.PostsRepository
import ke.co.postsmvvm.viewmodel.PostsViewModel
import ke.co.postsmvvm.viewmodel.PostsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PostItemClickListener {
    lateinit var postsViewModel: PostsViewModel
    lateinit var postsViewModelFactory: PostsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postsViewModelFactory = PostsViewModelFactory(PostsRepository())
        postsViewModel =
            ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)

        postsViewModel.getDbPosts()
    }

    override fun onResume() {
        super.onResume()
        postsViewModel.postsLiveData.observe(this, Observer { posts ->
            if (posts.isEmpty()) {
                if (isConnected(baseContext)) {
                    postsViewModel.getApiPosts()
                }
            } else {
                displayPosts(posts)
            }
        })

        postsViewModel.postsFailedLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })
    }

    fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            return true
        }
        return false
    }

    fun displayPosts(posts: List<Post>) {
        val postsAdapter = PostsRvAdapter(posts, this)
        rvPosts.apply {
            layoutManager = LinearLayoutManager(baseContext)
            adapter = postsAdapter
        }
    }

    override fun onItemClick(post: Post) {
        Toast.makeText(baseContext, post.title, Toast.LENGTH_LONG).show()
    }
}