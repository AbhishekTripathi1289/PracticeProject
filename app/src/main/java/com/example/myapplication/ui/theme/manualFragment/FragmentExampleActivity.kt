package com.example.myapplication.ui.theme.manualFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityFragmentExampleBinding
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.ui.BlogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, FragementA.getFragmentInstance("sag" , "dsfa"))
            .commit()
    }
}