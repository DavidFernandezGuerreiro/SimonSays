package com.example.david.simonsays

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.david.simonsays.R.id.botonAzul
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : Activity() {

    var botonSerie=Random() //numero random para la serie de parpadeos
    var listaColores: ArrayList<Int> = ArrayList<Int>()
    var numColor=0 //variable que recoge el numero random y se lo añade al ArrayList

    var botonPulsar=1 //esta variable indica el numero de botones se van a tener que pulsar (jugador)
    var numSerie=0 //con esta variable comparamos el botón pulsado (jugador) con la posición del Array

    var gameover=false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) //Para que no gire la pantalla

        val botonRojo = findViewById<Button>(R.id.botonRojo)
        val botonVerde = findViewById<Button>(R.id.botonVerde)
        val botonAmarillo = findViewById<Button>(R.id.botonAmarillo)
        val botonAzul = findViewById<Button>(R.id.botonAzul)
        val botonStart = findViewById<Button>(R.id.botonStart)

        val mitexto = findViewById<TextView>(R.id.mitexto)




        botonRojo.setOnClickListener{
            gameOver(0)
            botonRojo.setBackgroundColor(getResources().getColor(R.color.colorRojoDOS))
            Handler().postDelayed({botonRojo.setBackgroundColor(getResources().getColor(R.color.colorRojo))},300)
        }

        botonVerde.setOnClickListener{
            gameOver(1)
            botonVerde.setBackgroundColor(getResources().getColor(R.color.colorVerdeDOS))
            Handler().postDelayed({botonVerde.setBackgroundColor(getResources().getColor(R.color.colorVerde))},300)
        }

        botonAmarillo.setOnClickListener{
            gameOver(2)
            botonAmarillo.setBackgroundColor(getResources().getColor(R.color.colorAmarilloDOS))
            Handler().postDelayed({botonAmarillo.setBackgroundColor(getResources().getColor(R.color.colorAmarillo))},300)
        }

        botonAzul.setOnClickListener{
            gameOver(3)
            botonAzul.setBackgroundColor(getResources().getColor(R.color.colorAzulDOS))
            Handler().postDelayed({ botonAzul.setBackgroundColor(getResources().getColor(R.color.colorAzul)) }, 300)
        }
        /*botonAzul.setOnClickListener(
                object : View.OnClickListener {
                    override fun onClick(v: View) {
                        toast("Botón azul")
                        cambiarTexto(mitexto)
                        botonAzul.setBackgroundColor(getResources().getColor(R.color.colorAzulDOS))
                        Handler().postDelayed({ botonAzul.setBackgroundColor(getResources().getColor(R.color.colorAzul)) }, 800)
                    }
                }
        )*/

        botonStart.setOnClickListener{
            numSerie=0
            botonPulsar=1
            botonStart.setText("REINICIAR GAME")
            listaColores.clear()
            serieColores()
        }

    }


    fun serieColores(){

        //botonStart.isClickable=false
        numColor=botonSerie.nextInt(4) // Genera un número aleatorio entre 0 y 3
        listaColores.add(numColor) // Añade el número aletatorio (que representa un color) al ArrayList

        var delayed=2000L

        for(num in listaColores){

            when(num){
                0 -> {
                    Handler().postDelayed({botonRojo.setBackgroundColor(getResources().getColor(R.color.colorRojoDOS))
                        Handler().postDelayed({botonRojo.setBackgroundColor(getResources().getColor(R.color.colorRojo))},300)
                    },delayed)

                }
                1 -> {
                    Handler().postDelayed({botonVerde.setBackgroundColor(getResources().getColor(R.color.colorVerdeDOS))
                        Handler().postDelayed({botonVerde.setBackgroundColor(getResources().getColor(R.color.colorVerde))},300)
                    },delayed)

                }
                2 -> {
                    Handler().postDelayed({botonAmarillo.setBackgroundColor(getResources().getColor(R.color.colorAmarilloDOS))
                        Handler().postDelayed({botonAmarillo.setBackgroundColor(getResources().getColor(R.color.colorAmarillo))},300)
                    },delayed)

                }
                3 -> {
                    Handler().postDelayed({botonAzul.setBackgroundColor(getResources().getColor(R.color.colorAzulDOS))
                        Handler().postDelayed({botonAzul.setBackgroundColor(getResources().getColor(R.color.colorAzul))},300)
                    },delayed)

                }
            }
            delayed+=600
        }
        //Handler().postDelayed({botonStart.isClickable=true},delayed)
    }

    fun gameOver(boton:Int){

        if(numSerie<botonPulsar){

            if(listaColores.get(numSerie)==boton){

            }else{
                toast("HAS PERDIDO\nGAME OVER") // Mensaje que indica que ha perdido
                gameover=true;
            }
            numSerie=numSerie+1; // Aumenta uno en el número de la serie
        }

        if(numSerie==botonPulsar && gameover==false) {
            toast("CORRECTO") // mensaje que indica que la serie de colores fue la correcta
            numSerie=0 // cuando finaliza la serie de colores, le asignamos el valor 0 a la variable

            mitexto.setText("Score: "+botonPulsar.toString()) // Visualiza la "Score" del jugador
            botonPulsar++ // aumenta el número de botones que tiene que pulsar el jugador
            serieColores() // llamada al método
        }
        if(gameover==true){
            toast("HAS PERDIDO")
            gameover=false
        }

    }

    fun cambiarTexto(v:TextView){
        val formato= SimpleDateFormat("HH:mm:ss");
        val fechaActual=Calendar.getInstance().getTime();
        val s=formato.format(fechaActual);
        v.setText(String.format("Botón precionado: %s",s));
    }



}

