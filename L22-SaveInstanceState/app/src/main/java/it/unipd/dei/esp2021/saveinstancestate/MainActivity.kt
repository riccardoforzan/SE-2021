package it.unipd.dei.esp2021.saveinstancestate

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()
{
    // Class variables
    private lateinit var tv : TextView
    private lateinit var bu : Button

    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Display the layout
        setContentView(R.layout.activity_main)

        // Get references to the TextView and the button
        tv = findViewById(R.id.tv)
        bu = findViewById(R.id.bu)

        // Restore TextView state from the saved instance state
        if (savedInstanceState != null)
        {
            val strValue = savedInstanceState.getString("strTV")
            if (strValue != null) tv.text = strValue
        }

        // Set the action to be performed when the button is pressed
        bu.setOnClickListener { // Perform action on click
            tv.text = getString(R.string.goodjob)
        }
    }

    // Called when the system is about to pause the activity because it is
    // resuming a previous one. This method allows you to save any
    // dynamic INSTANCE state in your activity into the given Bundle,
    // to be later received in onCreate(Bundle) if the activity needs
    // to be re-created.
    // Note: PERSISTENT state (which is different from instance state!)
    // should be saved in the onPause() method because onSaveInstanceState()
    // is not part of the life cycle callbacks, hence it will not be called
    // in every situation
    override fun onSaveInstanceState(savedInstanceState: Bundle)
    {
        // Note: with the implementation of this method inherited from
        // AppCompatActivity, some widgets save their state in the bundle
        // by default.
        // Once the user interface contains AT LEAST one non-autosaving
        // element, you should provide a custom implementation of the method
        savedInstanceState.putString("strTV", tv.text.toString())
        super.onSaveInstanceState(savedInstanceState)
    }
}