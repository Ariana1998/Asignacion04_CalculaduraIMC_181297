package espinoza.ariana.asignacion04_calculaduraimc_181297

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    //Variables
    var txtResultado: TextView = findViewById(R.id.tvResultado)
    var txtEstado: TextView = findViewById(R.id.tvEstado)
    //Declaracion de los edit text
    val etEstatura: EditText = findViewById(R.id.etEstatura)
    val etPeso: EditText = findViewById(R.id.etPeso)
    val btnCalcular: Button = findViewById(R.id.btnCalcular)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Evento del boton
        btnCalcular.setOnClickListener{
            if(!this.etEstatura.text.isBlank() || !this.etPeso.text.isBlank()){
            //Se calcula el indice de masa corporal y se ubica el resultado
                val imcNum= this.calculaIMC(this.etEstatura.text.toString().toDouble(),
                    this.etPeso.text.toString().toDouble())
                this.txtResultado.setText(imcNum.toString())
                //Se obtiene el resultado del usuario
                val estado = this.obtenEstado(imcNum)
                this.txtEstado.setText(estado)
                //Se le incluye un color dependiendo del resultado que obtengan
                when(estado)
                {
                    "Bajo Peso" -> this.txtEstado.setBackgroundResource(R.color.brown)
                    "Saludable" -> this.txtEstado.setBackgroundResource(R.color.green)
                    "Sobrepeso" -> this.txtEstado.setBackgroundResource(R.color.greenish)
                    "Obesidad de grado 1" -> this.txtEstado.setBackgroundResource(R.color.yellow)
                    "Obesidad de grado 2" -> this.txtEstado.setBackgroundResource(R.color.orange)
                    "Obesidad de grado 3" -> this.txtEstado.setBackgroundResource(R.color.red)
                }

            }
        }

    }

    //Funcion que se encarga de calcular el imc en base al peso y la estatura del usuario
    fun calculaIMC(altura:Double, peso:Double): Double{
        val imc: Double = (peso /(Math.pow(altura,2.0)))
        return imc
    }

    //Funciona que se encarga de regresar el estado del usuario en base a su imc
    fun obtenEstado(imc: Double): String{
        when
        {
            imc <18.5 -> return "Bajo peso"
            imc >= 18.5 && imc <=24.9 -> return "Saludable"
            imc > 24.9 && imc <=29.9 -> return "Sobrepeso"
            imc > 29.9 && imc <=34.9 -> return "Obesidad de grado 1"
            imc > 34.9 && imc <=39.9 -> return "Obesidad de grado 2"
            imc >= 40 -> return "Obesidad grado 3"
        }
        return "error"
    }
}