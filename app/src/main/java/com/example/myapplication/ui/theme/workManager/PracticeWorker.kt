package com.example.myapplication.ui.theme.workManager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.random.Random

class PracticeWorker(context: Context,val  worerParams: WorkerParameters ): CoroutineWorker(context, worerParams) {
    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result = coroutineScope{
        Log.d("######", "${worerParams.inputData.getString("keyFirst")}")


        try {
            for(i in  0 ..5){
                delay(1000)
                var number = generateRandomNumber(1, 10000)
                Log.d("######", "$number")
            }
            Result.success(workDataOf("keySecond" to "Shivam Tripathi"))
        }
        catch (exception : Exception)
        {
            Result.failure()
        }
    }



    fun generateRandomNumber(min: Int, max: Int): Int {
        require(min <= max) { "Invalid range: min should be less than or equal to max" }

        return Random.nextInt(min, max + 1)
    }

}