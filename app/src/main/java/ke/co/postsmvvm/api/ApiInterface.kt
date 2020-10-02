package ke.co.postsmvvm.api

import ke.co.postsmvvm.models.Comment
import ke.co.postsmvvm.models.Post
import ke.co.postsmvvm.models.Todo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("posts/{post_id}/comments")
    suspend fun getComments(@Path("post_id") postId: Int): Response<List<Comment>>

    @GET("todos")
    suspend fun getTodos(): Response<List<Todo>>
}