package com.example.biodata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val bt_works = findViewById<AppCompatButton>(R.id.bt_works)
        bt_works.setOnClickListener { goToWorks() }
    }

    private fun goToWorks() {
        val intent = Intent(this, WorksActivity::class.java)
        startActivity(intent)
    }
}
