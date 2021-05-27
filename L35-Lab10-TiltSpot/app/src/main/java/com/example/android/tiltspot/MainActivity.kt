/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.tiltspot

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.Display
import android.view.Surface
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class MainActivity : AppCompatActivity(), SensorEventListener {
    // System sensor manager instance.
    private lateinit var mSensorManager: SensorManager

    // Accelerometer and magnetometer sensors, as retrieved from the
    // sensor manager.
    private var mSensorAccelerometer: Sensor? = null
    private var mSensorMagnetometer: Sensor? = null

    // Current data from accelerometer & magnetometer.  The arrays hold values
    // for X, Y, and Z.
    private var mAccelerometerData = FloatArray(3)
    private var mMagnetometerData = FloatArray(3)

    // TextViews to display current sensor values.
    private lateinit var mTextSensorAzimuth: TextView
    private lateinit var mTextSensorPitch: TextView
    private lateinit var mTextSensorRoll: TextView

    // ImageView drawables to display spots.
    private lateinit var mSpotTop: ImageView
    private lateinit var mSpotBottom: ImageView
    private lateinit var mSpotLeft: ImageView
    private lateinit var mSpotRight: ImageView

    // System display. Need this for determining rotation.
    private var mDisplay: Display? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextSensorAzimuth = findViewById(R.id.value_azimuth)
        mTextSensorPitch = findViewById(R.id.value_pitch)
        mTextSensorRoll = findViewById(R.id.value_roll)
        mSpotTop = findViewById(R.id.spot_top)
        mSpotBottom = findViewById(R.id.spot_bottom)
        mSpotLeft = findViewById(R.id.spot_left)
        mSpotRight = findViewById(R.id.spot_right)

        // Get accelerometer and magnetometer sensors from the sensor manager.
        // The getDefaultSensor() method returns null if the sensor
        // is not available on the device.
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        mSensorAccelerometer = mSensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER)
        mSensorMagnetometer = mSensorManager.getDefaultSensor(
                Sensor.TYPE_MAGNETIC_FIELD)

        // Get the display from the application context (for rotation).
        mDisplay = this.display
    }

    /**
     * Listeners for the sensors are registered in this callback so that
     * they can be unregistered in onStop().
     */
    override fun onStart() {
        super.onStart()

        // Listeners for the sensors are registered in this callback and
        // can be unregistered in onStop().
        //
        // Check to ensure sensors are available before registering listeners.
        // Both listeners are registered with a "normal" amount of delay
        // (SENSOR_DELAY_NORMAL).
        if (mSensorAccelerometer != null) {
            mSensorManager.registerListener(this, mSensorAccelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL)
        }
        if (mSensorMagnetometer != null) {
            mSensorManager.registerListener(this, mSensorMagnetometer,
                    SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onStop() {
        super.onStop()

        // Unregister all sensor listeners in this callback so they don't
        // continue to use resources when the app is stopped.
        mSensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        // The sensor type (as defined in the Sensor class).
        when (sensorEvent.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> mAccelerometerData = sensorEvent.values.clone()
            Sensor.TYPE_MAGNETIC_FIELD -> mMagnetometerData = sensorEvent.values.clone()
            else -> return
        }
        // Compute the rotation matrix: merges and translates the data
        // from the accelerometer and magnetometer, in the device coordinate
        // system, into a matrix in the world's coordinate system.
        //
        // The second argument is an inclination matrix, which isn't
        // used in this example.
        val rotationMatrix = FloatArray(9)
        val rotationOK = SensorManager.getRotationMatrix(rotationMatrix,
                null, mAccelerometerData, mMagnetometerData)

        // Remap the matrix based on current device/activity rotation.
        var rotationMatrixAdjusted = FloatArray(9)
        when (mDisplay?.rotation) {
            Surface.ROTATION_0 -> rotationMatrixAdjusted = rotationMatrix.clone()
            Surface.ROTATION_90 -> SensorManager.remapCoordinateSystem(rotationMatrix,
                    SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X,
                    rotationMatrixAdjusted)
            Surface.ROTATION_180 -> SensorManager.remapCoordinateSystem(rotationMatrix,
                    SensorManager.AXIS_MINUS_X, SensorManager.AXIS_MINUS_Y,
                    rotationMatrixAdjusted)
            Surface.ROTATION_270 -> SensorManager.remapCoordinateSystem(rotationMatrix,
                    SensorManager.AXIS_MINUS_Y, SensorManager.AXIS_X,
                    rotationMatrixAdjusted)
        }

        // Get the orientation of the device (azimuth, pitch, roll) based
        // on the rotation matrix. Output units are radians.
        val orientationValues = FloatArray(3)
        if (rotationOK) {
            SensorManager.getOrientation(rotationMatrixAdjusted,
                    orientationValues)
        }

        // Pull out the individual values from the array.
        val azimuth = orientationValues[0]
        var pitch = orientationValues[1]
        var roll = orientationValues[2]

        // Pitch and roll values that are close to but not 0 cause the
        // animation to flash a lot. Adjust pitch and roll to 0 for very
        // small values (as defined by VALUE_DRIFT).
        if (abs(pitch) < VALUE_DRIFT) {
            pitch = 0f
        }
        if (abs(roll) < VALUE_DRIFT) {
            roll = 0f
        }

        // Fill in the string placeholders and set the textview text.
        mTextSensorAzimuth.text = resources.getString(
                R.string.value_format, azimuth)
        mTextSensorPitch.text = resources.getString(
                R.string.value_format, pitch)
        mTextSensorRoll.text = resources.getString(
                R.string.value_format, roll)

        // Reset all spot values to 0. Without this animation artifacts can
        // happen with fast tilts.
        mSpotTop.alpha = 0f
        mSpotBottom.alpha = 0f
        mSpotLeft.alpha = 0f
        mSpotRight.alpha = 0f

        // Set spot color (alpha/opacity) equal to pitch/roll.
        // this is not a precise grade (pitch/roll can be greater than 1)
        // but it's close enough for the animation effect.
        if (pitch > 0) {
            mSpotBottom.alpha = pitch
        } else {
            mSpotTop.alpha = abs(pitch)
        }
        if (roll > 0) {
            mSpotLeft.alpha = roll
        } else {
            mSpotRight.alpha = abs(roll)
        }
    }

    /**
     * Must be implemented to satisfy the SensorEventListener interface;
     * unused in this app.
     */
    override fun onAccuracyChanged(sensor: Sensor, i: Int) {}

    companion object {
        // Very small values for the accelerometer (on all three axes) should
        // be interpreted as 0. This value is the amount of acceptable
        // non-zero drift.
        private const val VALUE_DRIFT = 0.05f
    }
}