package ingfabian.aguilamaps

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient

class UtilsMaps constructor( context_maps: Context){

    val context: Context
    var permisionMaps : PermisionMaps? = null

    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    init {
        context = context_maps
        permisionMaps = PermisionMaps(context)
    }

   /* @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (permisionMaps!!.checkPermissions()) {


            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        Toast.makeText(this, "Latitud: ${location.latitude.toString()} Longitud: ${location.longitude.toString()}", Toast.LENGTH_LONG).show()
                        // findViewById<TextView>(R.id.latTextView).text = location.latitude.toString()
                        // findViewById<TextView>(R.id.lonTextView).text = location.longitude.toString()
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }*/
}