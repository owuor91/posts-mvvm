package ke.co.postsmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ke.co.postsmvvm.models.Comment
import ke.co.postsmvvm.repository.CommentsRepository
import kotlinx.coroutines.launch


class CommentsViewModel(val commentsRepository: CommentsRepository): ViewModel() {
    var commentsLiveData = MutableLiveData<List<Comment>>()
    var commentsFailedLiveData = MutableLiveData<String>()

    fun getComments(postId: Int){
        viewModelScope.launch {
            val response = commentsRepository.getComments(postId)
            if(response.isSuccessful){
                commentsLiveData.postValue(response.body() as List<Comment>)
            }
            else{
                commentsFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

}