package com.example.headup2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    val floatingActionButton=findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val startbutton= findViewById<Button>(R.id.button)
        startbutton.setOnClickListener {
            val intent=Intent(this,MainActivity4::class.java)
            startActivity(intent)
        }
        floatingActionButton.setOnClickListener {
            val intent=Intent(this,CelActivity::class.java)
            startActivity(intent)
        }
    }
}