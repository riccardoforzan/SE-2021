package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment

class CrimeFragment: Fragment() {

    /**
     * lateinit modifier indicates that the initialization
     * of the var will be performed in another method, not in the
     * constructor of the class
     */

    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_crime, container, false)

        //Casting from view to CheckBox (subclass)
        solvedCheckBox = view.findViewById(R.id.crime_solved) as CheckBox

        titleField = view.findViewById(R.id.crime_title) as EditText

        dateButton = view.findViewById(R.id.crime_date) as Button
        dateButton.apply {
            text = crime.date.toString()
            //Making the button not clickable
            isEnabled = false
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        //Listener for the text changes
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This space intentionally left blank
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //Reflects the modificaitons on the Crime reference of this fragment
                crime.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                // This one too
            }
        }

        titleField.addTextChangedListener(titleWatcher)

        solvedCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked -> crime.isSolved = isChecked }
        }
    }
}