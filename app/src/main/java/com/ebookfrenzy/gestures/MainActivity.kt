package com.ebookfrenzy.gestures

import android.app.Service
import android.bluetooth.BluetoothClass
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Point
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener
{
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int)
    {
    }

    override fun onSensorChanged(event: SensorEvent?)
    {
        if (event != null)
        {
            Log.i ("sensor", "$(event.values[0]) , $(event.values[1]), $(event.values[2])")

            textView1.text = "X: $(event.values[0])"
            textView2.text = "Y: $(event.values[1])"
            textView3.text = "Z: $(event.values[2])"

        }
    }

    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        // get reference of the service
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        // focus in accelerometer
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        // setup the window
    }

    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(this, mAccelerometer,
                SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager!!.unregisterListener(this)
    }
}