package com.example

import android.app.Application
import android.widget.Toast

class DscApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Application created!", Toast.LENGTH_SHORT).show()
    }

}