package ingfabian.model

import android.location.Location

class CustomLocation {

    var oldVelocity : Float?
    var actualyVelocity : Float?

    var oldAceleration: Float?
    var newAceleration: Float?

     var oldLocation: Location?
     var newLocation: Location?

    init {
        oldLocation =null
        newLocation = null
        oldAceleration = null
        oldVelocity =  null
        actualyVelocity = null
        newAceleration = null

    }




}