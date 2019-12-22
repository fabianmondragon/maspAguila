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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import ingfabian.model.CustomLocation
import kotlin.math.sqrt



class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
     ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnPoiClickListener {
    override fun onPoiClick(p0: PointOfInterest?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    var customLocation = CustomLocation()
    var managmentSensor : ManagmentSensor? = null
    var listaLocation = ArrayList<Location>(2)
    var locationOrigin = Location("")



    private  val TAG = "MapsActivity"
    var context: Context? = null
    var markerOptions: MarkerOptions? = null
    var origin: MarkerOptions? = null
    var destination: MarkerOptions? = null
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        context = applicationContext
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        managmentSensor = ManagmentSensor(this)
    }
    override
    fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnPoiClickListener(this);
        origin = MarkerOptions().position(LatLng(4.667426, -74.056624)).title("HSR Layout").snippet("origin")
        destination =   MarkerOptions().position(LatLng(4.672655, -74.054071)).title("HSR Layout").snippet("destination")
        mMap.addMarker(origin)
        mMap.addMarker(destination)
        SetPolyline()
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin!!.position, 15f))
        setListener ()
        locationOrigin!!.latitude =  4.672655
        locationOrigin!!.longitude = -74.056624
        customLocation.oldLocation = locationOrigin
        customLocation.oldVelocity = 0f
        grunOverlay()
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

                    addMarkerVelocity(calculateVelocity(location), location)
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

    fun setParams ( location: Location){
        if (customLocation.oldLocation== null){

        }

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

        velocity = sqrt(parameterRaiz)
        customLocation.oldVelocity = velocity

        customLocation.oldLocation = location
        return velocity

    }
    fun addMarkerVelocity (velocity : Float, location: Location){
        // Instantiates a new CircleOptions object and defines the center and radius
        val circleOptions = CircleOptions()
            .center(LatLng(location.latitude, location.longitude))
            .radius(1000.0) // In meters


// Get back the mutable Circle
        val circle = mMap.addCircle(circleOptions)

    }
    fun grunOverlay (){
        var mSydneyGroundOverlay = mMap.addGroundOverlay(
            GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.common_full_open_on_phone))
                .position(LatLng(4.667426, -74.056624), 1000f)
        )
        mSydneyGroundOverlay.tag = "hola"






    }





}
