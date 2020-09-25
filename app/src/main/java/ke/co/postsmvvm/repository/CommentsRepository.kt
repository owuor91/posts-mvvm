package ke.co.postsmvvm.repository

import ke.co.postsmvvm.api.ApiClient
import ke.co.postsmvvm.api.ApiInterface
import ke.co.postsmvvm.models.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CommentsRepository {
    suspend fun getComments(postId: Int): Response<List<Comment>> = withContext(Dispatchers.IO){
        val apiInterface = ApiClient.buildService(ApiInterface::class.java)
        val response = apiInterface.getComments(postId)
        return@withContext response
    }
}