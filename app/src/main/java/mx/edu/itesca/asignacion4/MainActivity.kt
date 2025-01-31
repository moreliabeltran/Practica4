package mx.edu.itesca.asignacion4

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val pesoK: TextView = findViewById(R.id.weight)
        val alturaE: TextView = findViewById(R.id.height)
        val imc: TextView = findViewById(R.id.imc)
        val rango: TextView = findViewById(R.id.range)
        val calcular: Button = findViewById(R.id.calcular)

        fun calcularIMC(height:Double, weight: Double): Double{
            return weight/(height*height)
        }

        calcular.setOnClickListener {
            //Convertir los kilos y estatura a doble
            var peso: Double = 0.0
            var estatura: Double = 0.0

            try{
                peso = pesoK.text.toString().toDouble()
                estatura = alturaE.text.toString().toDouble()
            } catch (e: java.lang.Exception){
                imc.setText("Debe ingresar valores reales")
                println(e)
            }

            var resultado = calcularIMC(estatura,peso)
            var formattedNumber = "%.2f".format(resultado)
            imc.setText(formattedNumber)

            var salud: String
            var color: Int

            when{
                resultado < 18.5 -> {
                    salud = "Bajo Peso"
                    color = R.color.colorRed
                }
                resultado <=24.9 -> {
                    salud = "Saludable"
                    color = R.color.colorGreenish
                }
                resultado <=29.9 ->{
                    salud = "Sobrepeso"
                    color = R.color.colorYellow
                }
                resultado <=34.9 ->{
                    salud = "Obesidad Grado 1"
                    color = R.color.colorOrange
                }
                resultado <=39.9 ->{
                    salud = "Obesidad Grado 2"
                    color = R.color.colorBrown
                }
                resultado >=40 ->{
                    salud = "Obesidad Grado 2"
                    color = R.color.colorRed
                }
                else -> {
                    salud="Error"
                    color=0
                }
            }

            rango.setBackgroundResource(color)
            rango.setText(salud)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}