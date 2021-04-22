package it.unipd.dei.esp2021.switchactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Activity1 : AppCompatActivity()
{
    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        val next : Button = findViewById(R.id.Button1)
        next.setOnClickListener { view ->
            val myIntent = Intent(view.context, Activity2::class.java)
            startActivityForResult(myIntent, 0)
        }
    }
}