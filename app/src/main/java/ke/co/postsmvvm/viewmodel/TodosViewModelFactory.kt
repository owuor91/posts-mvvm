package ke.co.postsmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ke.co.postsmvvm.repository.TodosRepository
import java.lang.IllegalArgumentException

class TodosViewModelFactory(val todosRepository: TodosRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodosViewModel::class.java)){
            return TodosViewModel(todosRepository) as T
        }

        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}