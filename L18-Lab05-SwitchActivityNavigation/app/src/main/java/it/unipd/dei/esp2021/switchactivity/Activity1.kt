package it.unipd.dei.esp2021.switchactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Activity1 : AppCompatActivity()
{
    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        if (savedInstanceState == null)
        {
            val fragment = Activity1Fragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.activity_1, fragment)
                .commit()
        }
    }
}