package es.josemaria.videojuego

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import es.josemaria.videojuego.R
import es.josemaria.videojuego.Preferencias
import android.widget.TextView
import es.josemaria.videojuego.Constantes
class MainActivity : AppCompatActivity() {

    private val caballero by lazy{ findViewById<Switch>(R.id.switchGenero) } //Contiene un String definido en @String, que puede ser "masculino" o "femenino".
    private val modoMovimiento by lazy{ findViewById<Switch>(R.id.switchModoMovimiento) } //Contiene un String definido en @String, que puede ser "discreto" o "continuo".
    private val preferencias by lazy{  getSharedPreferences("es.josemaria.videojuego_preferences", Context.MODE_PRIVATE) } //Archivo de preferencias, para no tener que estar cambiando continuamente la opción por defecto.
    private val textViewVersion by lazy{ findViewById<TextView>(R.id.gameVersion)} //Textview en el que pondremos la versión del juego
    /**
     * Establece el layout.
     * @param savedInstanceState no se usa explicitamente, bundle inicial de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.setContentView(es.josemaria.videojuego.R.layout.activity_main)

        //Obtengo y establezco la versión del juego
        textViewVersion.text= "v."+Constantes.gameVersion


    }

    /**
     * Se sobreescribe onStart para que la pantalla principal coja la opción seleccionada en la pantalla de preferencias al volver atrás.
     */
    override fun onStart() {
        super.onStart()
        //Si las preferencias establecen que el caballero es dragon, lo rescatamos para esta app. Y al contrario.
        if(preferencias.getBoolean("caballero",false)){
            caballero.isChecked=true;
            caballero.setText(R.string.dragon)
        }else{
            caballero.isChecked=false;
            caballero.setText(R.string.pegaso)
        }


    }

    /**
     * Evento onClick del botón jugar, que lanza la actividad AndroidLauncher que lanza el Juego
     * @param view vista que se cliqueó. El botón en este caso.
     */
    fun lanzarJuego(view: View) {
        val i = Intent(
                this,
                AndroidLauncher::class.java)
        var bundle: Bundle = Bundle();
        //Si es pegaso no va a estar "checked" el switch caballero
        //Si es dragon, si va a estarlo.
        bundle.putBoolean("caballero",caballero.isChecked)
        i.putExtras(bundle)
        this.startActivity(i)
    }

    /**
     * Evento onClick del switch de elección de caballero, que cambia el caballero entre pegaso o dragon, para comenzar así el juego.
     * @param view vista que se cliqueó. El switch en este caso.
     */
    fun cambiarCaballero(view: View) {
        if ((view as Switch).isChecked) {
            caballero.setText(R.string.dragon)
        } else {
            caballero.setText(R.string.pegaso)
        }
    }



    /**
     * Evento onclick del imageView de la llave inglesa. Lleva al usuario a la pantalla de preferencias.
     * @param view vista que se cliqueó. El imageView en este caso.
     */
    fun irAOpciones(view: View) {
        val i = Intent(this, Preferencias::class.java)
        startActivity(i)
    }
}

