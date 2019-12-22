package ingfabian.aguilamaps

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
     ActivityCompat.OnRequestPermissionsResultCallback {

    private  val TAG = "MapsActivity"
    var context: Context? = null

    var markerOptions: MarkerOptions? = null



    var origin: MarkerOptions? = null
    var destination: MarkerOptions? = null

    var latitude: Double = 0.toDouble()
    var longitude: Double = 0.toDouble()

    var location: Location? = null


    private lateinit var mMap: GoogleMap


    val PERMISSION_ID = 42
    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        context = applicationContext
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



    }
    override
    fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        origin = MarkerOptions().position(LatLng(4.667426, -74.056624)).title("HSR Layout").snippet("origin")
        destination =   MarkerOptions().position(LatLng(4.672655, -74.054071)).title("HSR Layout").snippet("destination")
        mMap.addMarker(origin)
        mMap.addMarker(destination)
        SetPolyline()
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin!!.position, 15f))
        setListener ()
        //fijarPurebas ()
    }

    @SuppressLint("MissingPermission")
    fun setListener (){
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationListener = object : LocationListener {

            override fun onLocationChanged(location: Location) {
                Log.i(TAG, "onLocationChange")
                Toast.makeText(context, "!!!ON_LOCATION_CHANGE", Toast.LENGTH_LONG).show()
                addMarker (location)
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
                Log.i(TAG, "onStatusChanged")
                Toast.makeText(context, "!!!ON_STATUS_CHANGED", Toast.LENGTH_LONG).show()
            }

            override fun onProviderEnabled(provider: String) {
                Log.i(TAG, "onProviderEnabled")
                Toast.makeText(context, "!!!ON_PROVIDER_ENABLED", Toast.LENGTH_LONG).show()
            }

            override fun onProviderDisabled(provider: String) {
                Log.i(TAG, "onProviderDisabled")
                Toast.makeText(context, "!!!ON_PROVIDER_DISABLED", Toast.LENGTH_LONG).show()
            }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0f,locationListener)
    }

    fun addMarker (location: Location){ markerOptions = MarkerOptions().position(LatLng(location.latitude, location.longitude)).title("HSR Layout").snippet("origin")
        if (mMap != null)
            mMap.addMarker(markerOptions)


    }

    fun SetPolyline () {
        val polygonLatLngList = ArrayList<LatLng>()

        polygonLatLngList.add(LatLng(4.667426, -74.056624))
        polygonLatLngList.add(LatLng(4.672655, -74.054071))

        mMap.addPolygon(
            PolygonOptions()
            .addAll(polygonLatLngList)
            .fillColor(Color.parseColor("#3bb2d0")))

    }

}
