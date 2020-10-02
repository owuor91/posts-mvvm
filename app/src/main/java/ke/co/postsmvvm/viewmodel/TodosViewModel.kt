package ke.co.postsmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ke.co.postsmvvm.models.Todo
import ke.co.postsmvvm.repository.TodosRepository
import kotlinx.coroutines.launch

class TodosViewModel(var todosRepository: TodosRepository) : ViewModel() {
    var todosLiveData = MutableLiveData<List<Todo>>()
    var todosFailedLiveData = MutableLiveData<String>()

    fun getApiTodos() {
        viewModelScope.launch {
            val response = todosRepository.getTodos()
            if (response.isSuccessful) {
                todosLiveData.postValue(response.body())
            } else {
                todosFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}