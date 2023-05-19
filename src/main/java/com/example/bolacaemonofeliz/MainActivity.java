package com.example.bolacaemonofeliz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public Juego juego;
    private Handler handler = new Handler();
    TextView txtViewScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DATA BINDING
        juego = (Juego) findViewById(R.id.Pantalla);

        ViewTreeObserver obs = juego.getViewTreeObserver();

        obs.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Sólo se puede averiguar el ancho y alto una vez ya se ha pintado el layout. Por eso se calcula en este listener
                juego.ancho = juego.getWidth();
                juego.alto = juego.getHeight();
                juego.posX=juego.ancho/2;
                juego.posY=juego.alto-1980;
                juego.radio=80;
                /*juego.posMonedaY = 700;
                juego.posMoneda2Y = 700;
                juego.posMoneda3Y = 700;*/
            }
        });

        //Ejecutamos cada 20 milisegundos
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        //Cada x segundos movemos las monedas
                        juego.posMonedaY-=30;  //VELOCIDADES DE CADA OBJETO
                        juego.posMoneda2Y -= 41;
                        juego.posMoneda3Y -= 35;
                        juego.posMoneda4Y -= 45;

                        //refresca la pantalla y llama al draw
                        juego.invalidate();
                    }
                });
            }
        }, 0, 30);

    }
}