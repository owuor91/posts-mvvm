package ke.co.postsmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ke.co.postsmvvm.R

class CommentsActivity : AppCompatActivity() {
    var postId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        postId = intent.getIntExtra("POST_ID", 0)

    }
}