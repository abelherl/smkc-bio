package com.example.biodata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bt_about = findViewById<AppCompatImageButton>(R.id.bt_info)
        bt_about.setOnClickListener{
            goToAbout()
        }
    }

    private fun goToAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }
}
