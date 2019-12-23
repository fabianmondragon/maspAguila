# maspAguila


# Cómo funciona?

1. Lo primero que se debe realizar es bajar el repositorio y compilarlo. Para ello clone el repositorio y lo puede ejectura
desde la rama master

2. Se debe configurar una aplicación Fake GPS (Fake GPS Pro https://play.google.com/store/apps/details?id=com.usefullapps.fakegpslocationpro&hl=es)

3. Cambiar la ubilicación y observar como en la aplicaicón se crea el respectivo Marker y como cambia el valor de la velocidad


# Para el manejo de repositorio se utilizo Git Flow creandose las siguiente ramas
 - Develop
 - feature/dinamicMarkers
 - feature/iconCircle
 - Master


# Ejercicio!


1. Mostrar en un mapa la posición en tiempo real del móvil siguiendo una línea recta (puede ser desde 
Mi Águila [4.667426, -74.056624] hasta
Parque Virrey [4.672655, -74.054071]), con cada nueva ubicación GPS añadir un marcador sin reemplazar el anterior y otro marcador el cual
debe estar dentro de la línea, se debe usar GPS y opcional otros sensores como acelerómetro, giroscopio.

Solución:
Se utilizo un FakeGPS (Fake GPS Location Pro), el cual permitió realizar el cambio de ubicación, que fue identificado  por 
el listener correspondiente de la aplicación, permitiendo fijar el marker. Además se utilizó las ubicaciones sugerida.

2. En la misma pantalla mostrar la velocidad del usuario en un View Circular, ésta debe ser calculada usando en conjunto los sensores
disponibles en el teléfono.

Solución
Se utilizó la siguiente formula 𝑣f^2=𝑣i^+2·𝑎·∆𝑥, con el condicionante que la vi = 0 en un primer momento en t0. 



