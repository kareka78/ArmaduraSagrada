package es.josemaria.videojuego

import android.os.Bundle
import android.preference.PreferenceActivity

class Preferencias : PreferenceActivity() {

    /**
     * Método que enlaza la actividad con el fichero preferencias de res/xml
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.addPreferencesFromResource(R.xml.preferencias)
    }
}