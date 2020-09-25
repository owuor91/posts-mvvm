package ke.co.postsmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ke.co.postsmvvm.models.Comment
import ke.co.postsmvvm.repository.CommentsRepository
import kotlinx.coroutines.launch


class CommentsViewModel(val commentsRepository: CommentsRepository): ViewModel() {
    lateinit var commentsLiveData: LiveData<List<Comment>>
    var commentsFailedLiveData = MutableLiveData<String>()

    fun getApiComments(postId: Int){
        viewModelScope.launch {
            val response = commentsRepository.getComments(postId)
            if (!response.isSuccessful){
                commentsFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

    fun getDbComments(postId: Int){
        commentsLiveData = commentsRepository.getCommentsByPostId(postId)
    }

}