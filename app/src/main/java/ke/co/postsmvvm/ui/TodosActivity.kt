package ke.co.postsmvvm.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ke.co.postsmvvm.R
import ke.co.postsmvvm.repository.TodosRepository
import ke.co.postsmvvm.viewmodel.TodosViewModel
import ke.co.postsmvvm.viewmodel.TodosViewModelFactory
import kotlinx.android.synthetic.main.activity_todos.*

class TodosActivity : AppCompatActivity() {
    lateinit var todosViewModel: TodosViewModel
    lateinit var todosViewModelFactory: TodosViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos)

        todosViewModelFactory = TodosViewModelFactory(TodosRepository())
        todosViewModel =
            ViewModelProvider(this, todosViewModelFactory).get(TodosViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        todosViewModel.getApiTodos()

        todosViewModel.todosLiveData.observe(this, Observer { todosList ->
            rvTodos.apply {
                layoutManager = LinearLayoutManager(baseContext)
                adapter = TodosRvAdapter(todosList)
            }
        })

        todosViewModel.todosFailedLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })
    }
}