package com.example.myapplication.ui.theme.todoCodingInFlow.ui.task

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentTasksBinding
import com.example.myapplication.ui.theme.todoCodingInFlow.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

class TaskFragment: Fragment(R.layout.fragment_tasks), TasksAdapter.OnItemClickListener
{

    private val viewModel:TaskViewModel by activityViewModels()
    lateinit var taskAdapter: TasksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var binding = FragmentTasksBinding.bind(view)
        taskAdapter = TasksAdapter(this)

        binding.recyclerViewTasks.apply {

            adapter = taskAdapter
            setHasFixedSize(true)
        }


        viewModel.todoTask.observe(requireActivity()){

            taskAdapter.submitList(it)
        }


        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_fragment_tasks, menu)

        var search = menu.findItem(R.id.action_search)
        var actionView = search.actionView as SearchView
        actionView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               return  true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                 newText?.let {
                     viewModel.searchQuery.value  = it
                 }
               return true
            }

        })


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean{
    return when (item.itemId) {
    R.id.action_sort_by_name -> {
        true
    }
    R.id.action_sort_by_date_created -> {
        true
    }
    R.id.action_hide_completed_tasks -> {
        item.isChecked = !item.isChecked

        true
    }
    R.id.action_delete_all_completed_tasks -> {
        true
    }
    else -> super.onOptionsItemSelected(item)
    }
    }



    override fun onItemClick(task: Task) {
        TODO("Not yet implemented")
    }

    override fun onCheckBoxClick(task: Task, isChecked: Boolean) {
        TODO("Not yet implemented")
    }
}