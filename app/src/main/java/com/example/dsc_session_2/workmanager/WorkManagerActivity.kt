package com.example.dsc_session_2.workmanager

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.dsc_session_2.R
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity(), View.OnClickListener {
    private var runtimePermission: RunTimePermission = RunTimePermission(this)
    private val workManager = WorkManager.getInstance()

    private val oneTimeWorkerBtn: Button by lazy {
        findViewById(R.id.oneTimeWorker)
    }

    private val progress: LinearLayout by lazy {
        findViewById(R.id.llProgress)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("nhp", "onCreated")
        setContentView(R.layout.activity_work_manager)

        oneTimeWorkerBtn.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        Log.d("nhp", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("nhp", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("nhp", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("nhp", "onStop")
    }

    override fun onDestroy() {
        Log.d("nhp", "onDestroy")
        super.onDestroy()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.oneTimeWorker -> {
                runtimePermission.requestPermission(listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    object : RunTimePermission.PermissionCallback {
                        override fun onGranted() {
                            startOneTimeWorkManager()
                        }

                        override fun onDenied() {
                        }
                    })
            }
        }
    }


    private fun startOneTimeWorkManager() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val task = OneTimeWorkRequest.Builder(DownLoadFileWorkManager::class.java)
            .setConstraints(constraints).build()
        workManager.enqueue(task)

        workManager.getWorkInfoByIdLiveData(task.id)
            .observe(this, Observer {
                it?.let {

                    if (it.state == WorkInfo.State.RUNNING) {
                        loaderShow(true)
                    } else
                        if (it.state.isFinished) {
                            Toast.makeText(
                                this,
                                "DONE!",
                                Toast.LENGTH_SHORT
                            ).show()
                            loaderShow(false)
                        }
                }
            })
    }

    private fun loaderShow(flag: Boolean) {
        when (flag) {
            true -> progress.visibility = View.VISIBLE
            false -> progress.visibility = View.GONE
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST)
            runtimePermission.onRequestPermissionsResult(grantResults)

    }
}