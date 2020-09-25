package ke.co.postsmvvm.repository

import androidx.lifecycle.LiveData
import ke.co.postsmvvm.PostsApp
import ke.co.postsmvvm.api.ApiClient
import ke.co.postsmvvm.api.ApiInterface
import ke.co.postsmvvm.database.PostAppDatabase
import ke.co.postsmvvm.models.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CommentsRepository {
    suspend fun getComments(postId: Int): Response<List<Comment>> = withContext(Dispatchers.IO) {
        val apiInterface = ApiClient.buildService(ApiInterface::class.java)
        val response = apiInterface.getComments(postId)

        if (response.isSuccessful) {
            saveComments(response.body() as List<Comment>)
        }

        return@withContext response
    }

    suspend fun saveComments(commentsList: List<Comment>) = withContext(Dispatchers.IO) {
        val database = PostAppDatabase.getDbInstance(PostsApp.appContext)
        val commentDao = database.commentsDao()
        commentsList.forEach { comment ->
            commentDao.insertComment(comment)
        }
    }

    fun getCommentsByPostId(postId: Int): LiveData<List<Comment>> {
        val database = PostAppDatabase.getDbInstance(PostsApp.appContext)
        val commentDao = database.commentsDao()
        return commentDao.getCommentsByPostId(postId)
    }
}