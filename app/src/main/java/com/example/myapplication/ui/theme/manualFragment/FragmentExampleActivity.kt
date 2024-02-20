package com.example.myapplication.ui.theme.manualFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.ui.unit.Constraints
import androidx.fragment.app.activityViewModels
import androidx.work.BackoffPolicy
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.await
import androidx.work.workDataOf
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityFragmentExampleBinding
import com.example.myapplication.ui.theme.codingwithMitchhiltCaching.model.ui.BlogViewModel
import com.example.myapplication.ui.theme.workManager.PracticeWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class FragmentExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentExampleBinding
    private lateinit var workManager: WorkManager
    private lateinit var workRequest : OneTimeWorkRequest
    private lateinit var perWorkRequest : PeriodicWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        workManager = WorkManager.getInstance(this)
        val constraints = androidx.work.Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val data = workDataOf("keyFirst" to "Abhishek")

        checkWorker()

        workRequest = OneTimeWorkRequestBuilder<PracticeWorker>()
            .addTag("Tag123")
            .setConstraints(constraints)
            .setInputData(data)
            .setBackoffCriteria(
            BackoffPolicy.EXPONENTIAL,
            OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
            TimeUnit.MILLISECONDS
         ).build()

        perWorkRequest = PeriodicWorkRequestBuilder<PracticeWorker>(10, TimeUnit.MILLISECONDS).build()


        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this){workInfo ->
            when(workInfo.state){
                WorkInfo.State.ENQUEUED -> {
                    Log.d("######", "Worker Enqueued")
                }
                WorkInfo.State.RUNNING -> {
                    Log.d("######", "Worker Running")
                }
                WorkInfo.State.SUCCEEDED -> {
                    Log.d("######", "Worker Succeeed${workInfo.outputData.getString("keySecond")}")
                }
                WorkInfo.State.FAILED -> {
                    Log.d("######", "WOrker Failed")

                }
                WorkInfo.State.BLOCKED -> {
                    Log.d("######", "Worker Bloced")
                }
                WorkInfo.State.CANCELLED -> {
                    Log.d("######", "Worker Cancelled")
                }
            }

        }

        binding.button4.setOnClickListener{
            workManager.cancelUniqueWork("work123")
        }
        binding.button3.setOnClickListener{
            workManager.enqueue( perWorkRequest)
        }

        /*supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, FragementA.getFragmentInstance("sag" , "dsfa"))
            .commit()*/
    }

    private fun checkWorker() {
        CoroutineScope(Dispatchers.IO).launch {
            var workInfo = workManager.getWorkInfosForUniqueWork("work123").await()
            if(workInfo.size == 1)
            {
                val workInfo = workInfo[0]!!


                binding.textView2.text = workInfo.state.name
            }
        }
    }
}