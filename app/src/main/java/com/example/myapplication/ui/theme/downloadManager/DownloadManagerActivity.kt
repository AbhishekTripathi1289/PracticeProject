package com.example.myapplication.ui.theme.downloadManager

import android.Manifest
import android.annotation.TargetApi
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDownloadManagerBinding

class DownloadManagerActivity : AppCompatActivity() {
    val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 101
    var downloadID = 0L
    val  FILE_URL = "https://sample-videos.com/img/Sample-jpg-image-50kb.jpg"
    private lateinit var binding: ActivityDownloadManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDownloadManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button5.setOnClickListener{
            startDownload(FILE_URL)
        }

        registerReceiver(onDownloadComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

    }

    fun startDownload(url:String)
    {
        var permissionCheck = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                this, arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
            )
        } else
        {
           downloadPdfFile(FILE_URL)
        }

    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (permissions != null) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadPdfFile(FILE_URL)

            } else {
            }
        }
    }


    private val onDownloadComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

                when (intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)) {
                downloadID ->
                {
                    var status =  getDownloadingStatusById(context, downloadID)
                    Log.d("#####", "File Status $status")
                }
            }
        }
    }



    private fun downloadPdfFile(url: String) {
        try {
            val downloadManager =
                this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val uri = Uri.parse(url)
            val request = DownloadManager.Request(uri)
            request.setTitle("Download_mngr_file")
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "reward.pdf")
            downloadID = downloadManager.enqueue(request)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Unable to download file", Toast.LENGTH_LONG).show()
        }
    }


    fun getDownloadingStatusById(context: Context, id: Long): Int {
        val query = DownloadManager.Query()
        query.setFilterById(id)
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val cursor = downloadManager.query(query)
        if (cursor.moveToFirst()) {
            val statusColumnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
            val status = cursor.getInt(statusColumnIndex)
            when (status) {
                DownloadManager.STATUS_SUCCESSFUL ->
                    return DownloadManager.STATUS_SUCCESSFUL
                else -> return DownloadManager.STATUS_FAILED
            }
        } else {
            return DownloadManager.STATUS_FAILED
        }
    }

    private fun cancelDownload(context: Context, vararg downloadId: Long) {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.remove(*downloadId)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(onDownloadComplete)
    }
}