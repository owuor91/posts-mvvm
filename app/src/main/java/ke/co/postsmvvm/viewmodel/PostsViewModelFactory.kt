package ke.co.postsmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ke.co.postsmvvm.repository.PostsRepository
import java.lang.IllegalArgumentException

class PostsViewModelFactory(private val postsRepository: PostsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsViewModel::class.java)){
            return PostsViewModel(postsRepository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}