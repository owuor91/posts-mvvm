package ke.co.postsmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ke.co.postsmvvm.models.Post
import ke.co.postsmvvm.repository.PostsRepository
import kotlinx.coroutines.launch

class PostsViewModel(val postsRepository: PostsRepository) : ViewModel() {
    var postsFailedLiveData = MutableLiveData<String>()
    lateinit var postsLiveData: LiveData<List<Post>>


    fun getApiPosts() {
        viewModelScope.launch {
            val response = postsRepository.getPosts()
            if (response.isSuccessful) {
                postsRepository.savePosts(response.body() as List<Post>)
            } else {
                postsFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }


    fun getDbPosts() {
        postsLiveData = postsRepository.getDbPosts()
    }
}