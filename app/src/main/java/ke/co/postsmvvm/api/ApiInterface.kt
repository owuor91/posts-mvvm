package ke.co.postsmvvm.api

import ke.co.postsmvvm.models.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}