package com.example.todo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.TasksBinding

class TaskAdapter(var dataBase: List<TableTask>) : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    private val taskList = dataBase

    class TaskHolder(item: View) : RecyclerView.ViewHolder(item){
        val binding = TasksBinding.bind(item)
        fun bind(task: TableTask) = with(binding){
            title.text = task.title
            about.text = task.about
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tasks, parent, false)
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        var promTitle = taskList[position].title
        var promAbout = taskList[position].about

        if (taskList[position].title.length > 15)
            promTitle = taskList[position].title.substring(0,15) + "..."
        if (taskList[position].about.length > 70)
            promAbout = taskList[position].about.substring(0,70) + "..."

        holder.bind(TableTask(position, promTitle, promAbout)) //null

        holder.itemView.setOnClickListener{
            val transition = Intent(holder.itemView.context, Delete::class.java)
            transition.putExtra("id", position)
            transition.putExtra("title", taskList[position].title)
            transition.putExtra("about", taskList[position].about)
            holder.itemView.context.startActivity(transition)
        }
    }

    override fun getItemCount(): Int {
        return taskList.count()
    }
}