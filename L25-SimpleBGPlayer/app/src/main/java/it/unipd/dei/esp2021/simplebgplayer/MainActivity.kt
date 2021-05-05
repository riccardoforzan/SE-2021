package it.unipd.dei.esp2021.simplebgplayer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()
{
    private lateinit var buPlay: Button
    private lateinit var buStop: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Play button: starts the playback music service
        buPlay = findViewById(R.id.PlayButton)
        buPlay.setOnClickListener {
            val i = Intent(applicationContext, PlayerService::class.java)
            i.putExtra(PlayerService.PLAY_START, true)
            startService(i)
        }

        // Stop button: stops the music by stopping the service
        buStop = findViewById(R.id.StopButton)
        buStop.setOnClickListener {
            val i = Intent(applicationContext, PlayerService::class.java)
            stopService(i)
        }
    }
}