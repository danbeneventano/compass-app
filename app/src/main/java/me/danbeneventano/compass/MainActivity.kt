package me.danbeneventano.compass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.RotateAnimation
import android.view.animation.Animation

class MainActivity : AppCompatActivity(), SensorEventListener {

    private var currentDegree = 0f
    private lateinit var mSensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val degree = Math.round(event.values[0]).toFloat()
        val rotateAnimation = RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f)
        rotateAnimation.duration = 210
        rotateAnimation.fillAfter = true
        compass.startAnimation(rotateAnimation)
        currentDegree = -degree
    }

    override fun onAccuracyChanged(event: Sensor, accuracy: Int) {

    }
}
