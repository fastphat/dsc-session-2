package com.example.dsc_session_2.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream
import java.net.URL

const val PERMISSION_REQUEST = 100
const val URL_FILE =
    "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"

class DownLoadFileWorkManager(
    val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        try {
            val dir = context.filesDir.absolutePath + File.separator + "images"
            val fileDir = File(dir)
            if (!fileDir.exists()) {
                fileDir.mkdirs()
            }

            val path = dir + File.separator + "dsc.jpg"
            val file = File(path)
            if (!file.exists()) {
                file.createNewFile()
            }

            URL(URL_FILE).openStream().use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
            }
        } catch (e: Exception) {
            return Result.retry()
        }

        return Result.success()
    }

}