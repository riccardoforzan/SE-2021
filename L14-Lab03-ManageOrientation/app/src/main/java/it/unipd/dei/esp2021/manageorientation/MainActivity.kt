package it.unipd.dei.esp2021.manageorientation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        // The toggle buttons (ToggleButton class) auto-save and auto-restore
        // their instance state using the savedInstanceState Bundle.
        // Since corresponding toggle buttons have the same name in the
        // portrait and landscape layouts, their state is correctly auto-managed:
        // there is no need to write any custom code
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}