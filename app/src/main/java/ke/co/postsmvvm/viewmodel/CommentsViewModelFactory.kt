package ke.co.postsmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ke.co.postsmvvm.repository.CommentsRepository
import java.lang.IllegalArgumentException

class CommentsViewModelFactory(val commentsRepository: CommentsRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentsViewModel::class.java)){
            return CommentsViewModel(commentsRepository) as T
        }

        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}