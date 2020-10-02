package ke.co.postsmvvm.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ke.co.postsmvvm.R
import ke.co.postsmvvm.models.Todo
import kotlinx.android.synthetic.main.row_todos_item.view.*

class TodosRvAdapter(var todosList: List<Todo>) :
    RecyclerView.Adapter<TodosRvAdapter.TodosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        var rowView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_todos_item, parent, false)
        return TodosViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        val todo = todosList.get(position)
        holder.rowView.todoCheckBox.text = todo.title
        if (todo.completed == true) {
            holder.rowView.todoCheckBox.isChecked = true
        } else {
            holder.rowView.todoCheckBox.isChecked = false
        }
    }

    override fun getItemCount(): Int {
        return todosList.size
    }

    class TodosViewHolder(val rowView: View) : RecyclerView.ViewHolder(rowView)
}