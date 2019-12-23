# maspAguila


# Cómo funciona?

1. Lo primero que se debe realizar es bajar el repositorio y compilarlo. Para ello clone el repositorio y ejecutelo desde la rama master

2. Se debe bajar una aplicación Fake GPS (Fake GPS Pro https://play.google.com/store/apps/details?id=com.usefullapps.fakegpslocationpro&hl=es) y configurarla en sus dispositivo móvil.

3. Cambiar la ubilicación y observar como en la aplicaicón se crea el respectivo Marker y como cambia el valor de la velocidad

### nota:
La ubicación que se "quemo" en la aplicación corresponde con la dada en el mismo ejercicio; punto origen [4.667426, -74.056624]; punto de destino [4.672655, -74.054071]. Por lo cual, el cambio de ubicación a partir del FAKE GPS, debe darse entre estos dos puntos.


# Para el manejo de repositorio se utilizo Git Flow creandose las siguiente ramas
 - Develop
 - feature/dinamicMarkers
 - feature/iconCircle
 - Master


# Ejercicios!


1. Mostrar en un mapa la posición en tiempo real del móvil siguiendo una línea recta (puede ser desde 
Mi Águila [4.667426, -74.056624] hasta
Parque Virrey [4.672655, -74.054071]), con cada nueva ubicación GPS añadir un marcador sin reemplazar el anterior y otro marcador el cual
debe estar dentro de la línea, se debe usar GPS y opcional otros sensores como acelerómetro, giroscopio.

### Solución:
Se utilizó un FakeGPS (Fake GPS Location Pro), el cual permitió realizar el cambio de ubicación, que fue identificado  por 
el listener correspondiente de la aplicación, permitiendo fijar el marker. Además se utilizó las ubicaciones sugerida.

2. En la misma pantalla mostrar la velocidad del usuario en un View Circular, ésta debe ser calculada usando en conjunto los sensores
disponibles en el teléfono.

### Solución:
Se utilizó la siguiente formula 𝑣f^2=𝑣i^+2·𝑎·∆𝑥, con el condicionante que la vi = 0 en un primer momento en t0. Y se creo el View personalizado para mostrar la velocidad.

![alt text](https://github.com/fabianmondragon/maspAguila/blob/master/WhatsApp%20Image%202019-12-23%20at%2010.24.07%20AM.jpeg)






