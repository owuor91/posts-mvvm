package ke.co.postsmvvm.repository

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
}