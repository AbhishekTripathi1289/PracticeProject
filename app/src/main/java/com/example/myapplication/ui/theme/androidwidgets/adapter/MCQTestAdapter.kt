package com.example.myapplication.ui.theme.androidwidgets.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R
import com.example.myapplication.databinding.McqTestItemBinding
import com.example.myapplication.databinding.QuestionOptionsItemsBinding
import com.example.myapplication.databinding.SujectiveQuestionItemBinding
import com.example.myapplication.ui.theme.androidwidgets.models.Question
import com.example.myapplication.ui.theme.androidwidgets.models.SubjectiveQuestion

class MCQTestAdapter(private val questions: List<Any>, var callback: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return if (viewType == 0) {
            QuestionItemViewHolder(
                McqTestItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            SubjectQuestionViewHOlder(
                SujectiveQuestionItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    inner class SubjectQuestionViewHOlder(var itemViewLayout: SujectiveQuestionItemBinding) :
        ViewHolder(itemViewLayout.root) {
        init {

        }

        fun bind(
            position: Int,
            context: Context,
            subjecQuantity: SubjectiveQuestion,
            callback: (String) -> Unit
        ) {
            itemViewLayout.question.text = subjecQuantity.questionText
            if (subjecQuantity.answerText.isEmpty()) {
                itemViewLayout.editText.setText(" ")

            } else {
                itemViewLayout.editText.setText(subjecQuantity.answerText)
            }
            itemViewLayout.editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    callback.invoke(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
        }
    }

    inner class QuestionItemViewHolder(var itemViewLayout: McqTestItemBinding) :
        ViewHolder(itemViewLayout.root) {
        init {

        }

        fun bind(position: Int, context: Context, question: Question) {
            itemViewLayout.question.text = question.questionText
            itemViewLayout.linearLayout.removeAllViews()

            question.options.forEachIndexed { index, option ->

                var binding = QuestionOptionsItemsBinding.inflate(LayoutInflater.from(context))

                if (question.userSelectedOption != null) {
                    if (question.userSelectedOption == index) {
                        if (question.userSelectedOption == question.correctOption) {
                            binding.textViewOptions.setBackgroundColor(
                                context.getResources().getColor(R.color.color_E94F61_no_change)
                            )
                        } else {
                            binding.textViewOptions.setBackgroundColor(
                                context.getResources().getColor(R.color.purple_700)
                            )
                        }
                    }
                }
                binding.textViewOptions.text = option
                binding.textViewOptions.setOnClickListener {
                    Log.d("#####", "Click listener for")
                    question.userSelectedOption = index
                    notifyItemChanged(position)
                    callback.invoke(position)
                }
                itemViewLayout.linearLayout.addView(binding.root)

            }

        }
    }


    override fun getItemCount(): Int {
        return questions.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (questions.get(position)) {
            is SubjectiveQuestion -> {
                1
            }

            is Question -> {
                0
            }

            else -> {
                0
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (holder.itemViewType == 0) {
            (questions.get(position) as? Question)?.let {
                (holder as QuestionItemViewHolder).bind(
                    position,
                    holder.itemView.context,
                    it
                )
            }
        } else {
            (questions.get(position) as? SubjectiveQuestion)?.let {
                (holder as SubjectQuestionViewHOlder).bind(
                    position,
                    holder.itemView.context,
                    it
                ) { data ->
                    it.answerText = data
                }
            }
        }

    }


}