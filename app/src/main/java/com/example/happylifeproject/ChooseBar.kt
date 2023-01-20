package com.example.happylifeproject

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout


class ActivityBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.choose_bar, this)

        val activitySelectionButton: Button = findViewById(R.id.activity_selection_button)
        val myActivitiesButton: Button = findViewById(R.id.my_activities_button)

        activitySelectionButton.setOnClickListener{
            val intent = Intent(context, YoungManActivity::class.java)
            context.startActivity(intent)
        }

        myActivitiesButton.setOnClickListener {
            val intent = Intent(context, MyActivity::class.java)
            context.startActivity(intent)
        }
    }
}