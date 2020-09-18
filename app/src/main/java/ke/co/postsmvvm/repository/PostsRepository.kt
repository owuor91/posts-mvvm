package ke.co.postsmvvm.repository

import androidx.lifecycle.LiveData
import androidx.room.Room
import ke.co.postsapp.database.PostsDatabase
import ke.co.postsmvvm.PostsApp
import ke.co.postsmvvm.api.ApiClient
import ke.co.postsmvvm.api.ApiInterface
import ke.co.postsmvvm.models.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostsRepository() {
    suspend fun getPosts(): Response<List<Post>> = withContext(Dispatchers.IO + NonCancellable) {
        val apiClient = ApiClient.buildService(ApiInterface::class.java)
        val response = apiClient.getPosts()
        return@withContext response
    }

    suspend fun savePosts(postsList: List<Post>) = withContext(Dispatchers.IO) {
        val database = PostsDatabase.getInstance(PostsApp.appContext)
        postsList.forEach { post ->
            database.postsDao().insertPost(post)
        }
    }


    fun getDbPosts(): LiveData<List<Post>> {
        val database = PostsDatabase.getInstance(PostsApp.appContext)
        return database.postsDao().getPosts()
    }
}