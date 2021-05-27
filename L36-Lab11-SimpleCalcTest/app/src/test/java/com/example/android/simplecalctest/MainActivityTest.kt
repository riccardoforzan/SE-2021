package com.example.android.simplecalctest

import android.os.Build
import android.widget.EditText
import org.junit.Assert.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P) // Android SDKs 29,30 would require Java 9
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {
    private lateinit var activity: MainActivity
    private lateinit var et1: EditText

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java)
                   .create().start().resume()
                   .get()

        et1 = activity.findViewById(R.id.operand_one_edit_text)
    }

    @Test
    fun appName() {
        val hello = activity.resources.getString(R.string.app_name)
        assertEquals(hello, "SimpleCalcTest")
    }

    @Test
    fun operandConversion1_int() {
        et1.setText("3")
        val d = activity.getOperand(et1)
        assertEquals(d, 3.0, 0.0)
    }

    @Test
    fun operandConversion2_fp() {
        et1.setText("3.14")
        val d = activity.getOperand(et1)
        assertEquals(d, 3.14, 0.0)
    }

    @Test
    fun operandConversion3_negative() {
        et1.setText("-428.96")
        val d = activity.getOperand(et1)
        assertEquals(d, -428.96, 0.0)
    }

    @Test(expected = NumberFormatException::class)
    fun operandConversion4_empty() {
        et1.setText("")
        activity.getOperand(et1)
    }
}