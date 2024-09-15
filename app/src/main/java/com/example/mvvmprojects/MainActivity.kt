package com.example.mvvmprojects

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmprojects.apps.acontactManager.ContactManagerHomeActivity

class MainActivity : AppCompatActivity() {

    lateinit var btnContactManagerApp : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnContactManagerApp = findViewById(R.id.btnContactManagerApp)

        btnContactManagerApp.setOnClickListener {
            val intent = Intent(this, ContactManagerHomeActivity::class.java)
            startActivity(intent)
        }

    }
}