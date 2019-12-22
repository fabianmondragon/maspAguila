package ingfabian.aguilamaps

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class ManagmentSensor (context: Context ): SensorEventListener {

    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
    var context: Context? = null
    var oldAceleration : Float?
    var newAceleration : Float?



    init {
        this.context = context
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        oldAceleration = 0f
        newAceleration = 0f
    }








    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        var strings = ""

    }

    override fun onSensorChanged(p0: SensorEvent?) {
        oldAceleration = p0!!.values[0]

    }

    fun getAcceleration (): Float{
        return oldAceleration!!
    }





}