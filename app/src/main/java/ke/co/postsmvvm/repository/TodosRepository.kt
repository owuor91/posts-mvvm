package ke.co.postsmvvm.repository

import ke.co.postsmvvm.api.ApiClient
import ke.co.postsmvvm.api.ApiInterface
import ke.co.postsmvvm.models.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TodosRepository {
    suspend fun getTodos(): Response<List<Todo>> = withContext(Dispatchers.IO) {
        val apiInterface = ApiClient.buildService(ApiInterface::class.java)
        val response = apiInterface.getTodos()
        return@withContext response
    }

}