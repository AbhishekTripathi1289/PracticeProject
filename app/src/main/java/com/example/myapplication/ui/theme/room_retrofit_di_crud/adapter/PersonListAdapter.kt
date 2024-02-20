package com.example.myapplication.ui.theme.room_retrofit_di_crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.databinding.ItemRecyclerviewCrudBinding
import com.example.myapplication.ui.theme.room_retrofit_di_crud.model.Student

class PersonListAdapter(var studentList: List<Student>, var deleteCallback: (Student) -> Unit) : RecyclerView.Adapter<PersonListAdapter.PersonViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder
    {
        return PersonViewHolder(ItemRecyclerviewCrudBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    inner class PersonViewHolder(var itemViewLayout: ItemRecyclerviewCrudBinding) : ViewHolder(itemViewLayout.root)
    {
        init {
            itemViewLayout.imageViewDelete.setOnClickListener{
                deleteCallback.invoke(studentList.get(bindingAdapterPosition))

            }
        }
        fun bind(position: Int)
        {
            itemViewLayout.imageViewDelete.setOnClickListener{
                deleteCallback.invoke(studentList.get(adapterPosition))
                //code for locally delete student
               /* studentList.toMutableList().removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition);
                notifyItemRangeChanged(adapterPosition, studentList.size- adapterPosition);*/
            }
            itemViewLayout.textViewStudentName.text = studentList.get(position).name
        }
    }

    override fun getItemCount(): Int
    {
       return studentList.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(position)
    }

    fun updateList(it: ArrayList<Student>) {
        studentList = it
    }
}