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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import ingfabian.model.CustomLocation
import ingfabian.ui.IconGenerator
import ingfabian.util.Constant
import ingfabian.util.ManagmentPermission
import kotlin.math.sqrt



class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    var iconGenerator : IconGenerator? = null
    var customLocation = CustomLocation()
    var managmentSensor : ManagmentSensor? = null
    var locationOrigin = Location("")
    private  val TAG = "MapsActivity"
    var context: Context? = null
    var markerOptions: MarkerOptions? = null
    var origin: MarkerOptions? = null
    var destination: MarkerOptions? = null
    private lateinit var mMap: GoogleMap
    var managmentPermission = ManagmentPermission(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        context = applicationContext
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        managmentSensor = ManagmentSensor(this)
        iconGenerator = IconGenerator(this)
    }
    override
    fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        origin = MarkerOptions().position(LatLng(Constant.initialLatitud, Constant.initialLongitud)).title("HSR Layout").snippet("origin")
        destination =   MarkerOptions().position(LatLng(Constant.destiyLatitud, Constant.destyniLongitud)).title("HSR Layout").snippet("destination")
        mMap.addMarker(origin)
        mMap.addMarker(destination)
        SetPolyline()
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin!!.position, 16f))

        locationOrigin!!.latitude =  Constant.initialLatitud
        locationOrigin!!.longitude = Constant.initialLongitud
        customLocation.oldLocation = locationOrigin
        customLocation.oldVelocity = 0f

        if (managmentPermission.checkPermissions(this)) {
            if (isLocationEnabled()) {
                setListener()
            }
        }else
        {
            managmentPermission.requestPermissions(this)
        }

    }


    @SuppressLint("MissingPermission")
    fun setListener (){

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationListener = object : LocationListener {

            override fun onLocationChanged(location: Location) {
                Log.i(TAG, "onLocationChange")
                Toast.makeText(context, "!!!ON_LOCATION_CHANGE", Toast.LENGTH_LONG).show()
                if (customLocation.oldLocation != location) {
                    addMarker(location)

                    addMarkerVelocity(calculateVelocity(location), Constant.circleLatitud, Constant.circleLongitud)
                }

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

        polygonLatLngList.add(LatLng(Constant.initialLatitud, Constant.initialLongitud))
        polygonLatLngList.add(LatLng(Constant.destiyLatitud, Constant.destyniLongitud))

        mMap.addPolygon(
            PolygonOptions()
                .addAll(polygonLatLngList)
                .fillColor(Color.parseColor("#3bb2d0")))

    }


    fun getDistance (locationold: Location?, locationnew:Location): Float{

        return locationold!!.distanceTo(locationnew)
    }
    fun calculateVelocity (location: Location): Float {
        var velocity : Float
        var distance : Float
        var acceleration: Float
        var parameterRaiz : Float
        distance = getDistance(customLocation.oldLocation, location)
        acceleration = managmentSensor!!.getAcceleration()

        parameterRaiz = (customLocation.oldVelocity?.plus(((2*acceleration)* (distance)))!!)
        if (parameterRaiz < 0f) {
            velocity = 0f
        }
        else velocity = sqrt(parameterRaiz)

        customLocation.oldVelocity = velocity
        customLocation.oldLocation = location
        return velocity

    }
    fun addMarkerVelocity (velocity : Float, latitude: Double, longitude: Double){
        addIcon(iconGenerator!!, "V: $velocity", LatLng(latitude, longitude))


    }

    fun addIcon (iconFactory: IconGenerator, text: CharSequence, position: LatLng){
        var markerOptions = MarkerOptions().
            icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(text)))
            .position(position)
        mMap.addMarker(markerOptions)
    }


    fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    }


}
