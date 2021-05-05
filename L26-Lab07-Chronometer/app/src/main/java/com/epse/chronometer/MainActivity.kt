package com.epse.chronometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    lateinit var chronometer: Chronometer
    lateinit var btn: Button

    var isWorking: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chronometer = findViewById(R.id.chrono)
        btn = findViewById(R.id.button)
        isWorking = false

        btn.text = "Start chronometer"

        btn.setOnClickListener {
            if(!isWorking) startChronometer();
            else stopChronometer();
        }

    }

    fun startChronometer(){
        btn.text = "Stop chronometer"
        chronometer.start();
        isWorking = true;
    }

    fun stopChronometer(){
        btn.text = "Start chronometer"
        chronometer.stop();
        isWorking = false;
    }
}