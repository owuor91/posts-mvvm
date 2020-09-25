package ke.co.postsmvvm.repository

import androidx.lifecycle.LiveData
import ke.co.postsmvvm.PostsApp
import ke.co.postsmvvm.api.ApiClient
import ke.co.postsmvvm.api.ApiInterface
import ke.co.postsmvvm.database.PostAppDatabase
import ke.co.postsmvvm.models.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostsRepository() {
    suspend fun getPosts(): Response<List<Post>> = withContext(Dispatchers.IO + NonCancellable) {
        val apiClient = ApiClient.buildService(ApiInterface::class.java)
        val response = apiClient.getPosts()
        if (response.isSuccessful){
            val posts = response.body() as List<Post>
            savePosts(posts)
        }

        return@withContext response
    }

    suspend fun savePosts(postsList: List<Post>) = withContext(Dispatchers.IO){
        val database = PostAppDatabase.getDbInstance(PostsApp.appContext)
        postsList.forEach { post ->
            database.postDao().insertPost(post)
        }
    }

    fun getDbPosts(): LiveData<List<Post>>{
        val database = PostAppDatabase.getDbInstance(PostsApp.appContext)
        return database.postDao().getPosts()
    }

    fun getPostById(postId: Int): LiveData<Post>{
        val database = PostAppDatabase.getDbInstance(PostsApp.appContext)
        return database.postDao().getPostById(postId)
    }
}