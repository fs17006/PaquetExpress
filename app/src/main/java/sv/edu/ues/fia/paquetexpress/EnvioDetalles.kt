package sv.edu.ues.fia.paquetexpress

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout

class EnvioDetalles: AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envios_detalles)





    }

    fun salirAqui(view: View){
        val intent= Intent(this, MenuAdm::class.java).apply{}
        startActivity(intent)
    }



}