package com.example.myapplication.ui.theme.androidwidgets.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMcqtestBinding
import com.example.myapplication.ui.theme.androidwidgets.adapter.MCQTestAdapter
import com.example.myapplication.ui.theme.androidwidgets.models.Question
import com.example.myapplication.ui.theme.androidwidgets.models.SubjectiveQuestion

class MCQTestActivity : AppCompatActivity() {
    private lateinit var adapter: MCQTestAdapter
    private lateinit var binding: ActivityMcqtestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMcqtestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        val questions = generateQuestions()
        adapter = MCQTestAdapter(questions) {
            binding.recyclerView.scrollToPosition(it + 2)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun generateQuestions(): List<Any> {
        val questions = mutableListOf<Any>()

        // You can replace the sample questions and options with your actual data
        for (i in 1..5) {
            if (i % 2 == 0) {
                val questionText = "Question $i: What is the capital of country $i?"
                val options = listOf("Option A", "Option B", "Option C", "Option D")
                val correctOption = (0..3).random() // Randomly select correct option
                val question = Question(questionText, options, correctOption)
                questions.add(question)
            } else {
                val questionText = "Question $i: What is the capital of country $i?"
                var subjectiveQuestion = SubjectiveQuestion(questionText)
                questions.add(subjectiveQuestion)
            }
        }

        return questions
    }
}