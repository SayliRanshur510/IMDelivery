package com.ingrammicro.imdelivery.tasks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ingrammicro.imdelivery.Event
import com.ingrammicro.imdelivery.databinding.ItemTaskBinding
import com.ingrammicro.imdelivery.tasks.model.Task
import com.ingrammicro.imdelivery.tasks.viewmodel.TaskAllotedViewModel
import java.util.*

class TaskListAdapter(var viewModel: TaskAllotedViewModel,val position: Int,
                      val itemClickListener: (Task) -> Unit):
    RecyclerView.Adapter<TaskListAdapter.ViewHolder>(){


    interface OnItemClickListener {
        fun onItemClick(item: Task?)
    }
    private var taskList:ArrayList<Task> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemTaskListBinding = ItemTaskBinding.inflate(inflater)
        return ViewHolder(itemTaskListBinding)
    }

    fun addItems(task:ArrayList<Task>){
        taskList.addAll(task)
        notifyItemRangeChanged(taskList.size,task.size)
    }
    fun clear() {
        taskList.clear()
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: TaskListAdapter.ViewHolder, position: Int) {
        holder.bind(taskList[position],itemClickListener)
    }

    inner class ViewHolder(var itemTaskListBinding:ItemTaskBinding):
        RecyclerView.ViewHolder(itemTaskListBinding.root){

        fun bind(
            item: Task,itemClickListener: (Task) -> Unit
        ) {


            itemTaskListBinding.item = item
            itemTaskListBinding.executePendingBindings()

            itemTaskListBinding.textView.setOnClickListener {
                viewModel.getMap.value = Event(Unit)
            }

            itemTaskListBinding.textCall.setOnClickListener {
                viewModel.getContactNumber.value = Event(Unit)
            }

            itemTaskListBinding.root.setOnClickListener {
                itemClickListener(item)
            }



        }

    }



}


