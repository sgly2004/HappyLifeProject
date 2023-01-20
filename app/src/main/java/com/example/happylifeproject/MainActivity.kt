package com.example.happylifeproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnEnterOlder: Button = findViewById(R.id.btnEnterOlder)
        val btnEnterYoungMan: Button = findViewById(R.id.btnEnterYoungMan)

        fun View.setOnClickListener(listener: View.OnClickListener) {
            setOnClickListener(listener)
        }

        btnEnterOlder.setOnClickListener {
            val intent = Intent(this, OlderActivity::class.java)
            startActivity(intent)
        }

        btnEnterYoungMan.setOnClickListener {
            val intent = Intent(this, YoungManActivity::class.java)
            startActivity(intent)
        }
    }
}
