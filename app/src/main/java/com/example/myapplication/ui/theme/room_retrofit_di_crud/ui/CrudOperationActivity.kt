package com.example.myapplication.ui.theme.room_retrofit_di_crud.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.myapplication.databinding.ActivityMvvmWithRetrofitRoomBinding
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.Blog
import com.example.myapplication.ui.theme.room_retrofit_di_crud.adapter.PersonListAdapter
import com.example.myapplication.ui.theme.room_retrofit_di_crud.model.Student
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder


@AndroidEntryPoint
class CrudOperationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvvmWithRetrofitRoomBinding

    private val viewModel: CrudViewModel by viewModels()
    private lateinit var adapter: PersonListAdapter
    private var deleteCallback: (Student) -> Unit =  { student ->
        viewModel.deleteStudent(student)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMvvmWithRetrofitRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getAllStudent()

        viewModel.getAllBlogList()

        addObserver()

        addlisttener()

        setRecyclerViewAdapter()
    }

    private fun setRecyclerViewAdapter() {

        adapter = PersonListAdapter(emptyList(), deleteCallback)
        binding.recyclerView.layoutManager = LinearLayoutManager(this).apply { orientation  = LinearLayoutManager.VERTICAL }
        binding.recyclerView.adapter = adapter
    }

    private fun addlisttener() {
        binding.buttoAddStudent.setOnClickListener{
            viewModel.insertStudent(Student(name = "Abhsiek Tripathi"))
        }
    }

    private fun addObserver() {
        viewModel.blogsList.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception?.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }

                else -> {}
            }
        }

       /* viewModel.studentList.observe(this){
            when(it){
                is DataState.Error -> {
                  //  displayProgressBar(false)
                    Toast.makeText(this, "SomeThing went Wrong", Toast.LENGTH_LONG).show()
                }
                DataState.Loading -> {
                  //  displayProgressBar(true)

                }
                is DataState.Success -> {
                   // displayProgressBar(false)
                    Log.d("Student_Data", Gson().toJson( it.data))
                }
            }
        }
        */
          viewModel.studentList.observe(this){
              adapter.updateList(it as ArrayList)
              adapter.notifyDataSetChanged()
             }
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        binding.progressBar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayError(message: String?){
        if(message != null) binding.text.text = message else binding.text.text = "Unknown error."
    }

    private fun appendBlogTitles(blogs: List<Blog>){
        val sb = StringBuilder()
        for(blog in blogs){
            sb.append(blog.title + "\n")
        }
        binding.text.text = sb.toString()
    }
}