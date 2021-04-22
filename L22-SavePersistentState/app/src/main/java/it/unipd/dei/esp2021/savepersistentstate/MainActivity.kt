package it.unipd.dei.esp2021.savepersistentstate

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    // Class constants
    private val KEY_ETCOLOR = "editTextValue"
    private val KEY_SBSIZE = "seekBarValue"
    private val KEY_CBALLCAPS = "checkBoxValue"

    // Class variables, reference to widgets in the activity_main.xml in res/layout
    private lateinit var tv: TextView
    private lateinit var etText: EditText
    private lateinit var etColor: EditText
    private lateinit var sbSize: SeekBar
    private lateinit var cbAllCaps: CheckBox

    // Called once when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Display the layout
        setContentView(R.layout.activity_main)

        // Get references to widgets
        tv = findViewById(R.id.tv)
        etText = findViewById(R.id.et_text)
        etColor = findViewById(R.id.et_color)
        sbSize = findViewById(R.id.sb_size)
        cbAllCaps = findViewById(R.id.cb_allcaps)

        // Get persistent data stored as SharedPreferences
        val preferences = getPreferences(MODE_PRIVATE)

        /**
         * Set the relevant status of widgets according to persistent data, if no data use
         * default values provided as second argument of the call
         */
        etColor.setText(preferences.getString(KEY_ETCOLOR, getString(R.string.default_color)))
        sbSize.progress = preferences.getInt(KEY_SBSIZE, 0)
        cbAllCaps.isChecked = preferences.getBoolean(KEY_CBALLCAPS, false)

        // Set actions to be performed when the user provides new data

        // New text
        etText.setOnEditorActionListener { v, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    // Update text
                    updateText()
                    // Hide keyboard
                    val imm =
                        v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                    // Give up focus
                    v.clearFocus()
                    true
                }
                else -> false
            }
        }

        // New text color
        etColor.setOnEditorActionListener { v, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    // TODO: input validation!
                    // Update text
                    updateText()
                    // Hide keyboard
                    val imm =
                        v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                    // Give up focus
                    v.clearFocus()
                    true
                }
                else -> false
            }
        }

        // New text size
        sbSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update text
                updateText()
            }

            // OnSeekBarChangeListener is an interface,
            // so an implementation must be provided for all the methods
            override fun onStartTrackingTouch(seek: SeekBar) = Unit
            override fun onStopTrackingTouch(seek: SeekBar) = Unit
        })

        // New value for the "all caps" property
        cbAllCaps.setOnCheckedChangeListener { _, _ ->
            // Update text
            updateText()
        }
    }

    // Called every time the activity becomes active and ready to receive user input
    override fun onResume() {
        super.onResume()
        updateText()
    }

    /**Called every time the user no longer actively interacts with the activity,
     * but it is still visible on screen. The counterpart to onResume()
     * ALL SAVINGS SHOULD BE PERFORMED ON THIS METHOD, BECAUSE IS THE LAST METHOD
     * GUARANTEE TO BE CALLED WHEN THE APPLICATION IS NO MORE ON FOCUS.
     */
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onPause() {
        super.onPause()

        // Store values between instances here
        val preferences = getPreferences(MODE_PRIVATE)
        val editor = preferences.edit()

        // Store relevant status of the widgets that are part of the persistent state
        editor.putString(KEY_ETCOLOR, etColor.text.toString())
        editor.putInt(KEY_SBSIZE, sbSize.progress)
        editor.putBoolean(KEY_CBALLCAPS, cbAllCaps.isAllCaps)

        // Commit to storage synchronously
        editor.apply()
    }

    // Called to set the text attributes according to widget status
    private fun updateText() {
        tv.text = etText.text
        tv.setTextColor(Integer.parseUnsignedInt(etColor.text.toString(), 16))
        tv.setTextSize(1, sbSize.progress.toFloat())
        tv.isAllCaps = cbAllCaps.isChecked
    }
}