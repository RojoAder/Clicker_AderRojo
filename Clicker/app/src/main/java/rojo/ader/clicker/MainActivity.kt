package rojo.ader.clicker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    var cuenta:Int=0
    var cosa:String? = "pichadas"
    lateinit var numero: TextView
    lateinit var palabra:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_suma:Button = findViewById(R.id.plus)
        val btn_resta:Button = findViewById(R.id.minus)
        val btn_borrar:Button = findViewById(R.id.clear)
        palabra= findViewById(R.id.palabra)
        numero = findViewById(R.id.numero)

        btn_suma.setOnClickListener {
            cuenta++
            numero.setText("$cuenta")
        }

        btn_resta.setOnClickListener {
            cuenta--
            numero.setText("$cuenta")
        }

        btn_borrar.setOnClickListener {
            val alertDialog: AlertDialog? = this?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("Borrar") { dialog, id ->
                        cuenta = 0
                        numero.setText("$cuenta")
                    }
                    setNegativeButton("Cancelar",{dialog, id ->})
                }
                builder?.setMessage("¿Seguro que desea borrar la cuenta?").setTitle("ALERTA")

                builder.create()
            }

            alertDialog?.show()
        }

    }

    override fun onPause() {
        super.onPause()

        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        val editor=sharedPref.edit()

        cosa = palabra.text.toString()
        editor.putInt("contador",cuenta)
        editor.putString("cosa",cosa)
        editor.commit()
    }

    override fun onResume() {
        super.onResume()

        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        cuenta = sharedPref.getInt("contador",0)
        cosa = sharedPref.getString("cosa","palabra")
        numero.setText("$cuenta")
        palabra.setText(cosa)
    }
}