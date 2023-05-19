package com.example.bolacaemonofeliz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

// Extensión de una View. Totalmente necesario para dibujar
public class Juego extends View {
    public int ancho,alto;
    public float escala;
    public int posX,posY,radio,posMonedaX, posMonedaY, posMoneda2X, posMoneda2Y,
            posMoneda3X, posMoneda3Y, posMoneda4X, posMoneda4Y;
    private GestureDetector gestos;
    private RectF rectCesta, rectCesta2, rectCesta3, rectCesta4;
    private RectF rectMoneda, rectMoneda2, rectMoneda3, rectMoneda4;
    private Integer puntuacion = 0;
    private Random random = new Random();
    public Juego(Context context) {
        super(context);
    }
    public Juego(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //BITMAPS
    Bitmap bmCesta = BitmapFactory.decodeResource(getResources(), R.drawable.mono);
    Bitmap bmBanana1 = BitmapFactory.decodeResource(getResources(), R.drawable.platano);
    Bitmap bmBanana2 = BitmapFactory.decodeResource(getResources(), R.drawable.platanored);
    Bitmap bmBanana3 = BitmapFactory.decodeResource(getResources(), R.drawable.platanocyan);
    Bitmap bmBalon = BitmapFactory.decodeResource(getResources(), R.drawable.chepoalmono);


    //Sección que capta los eventos del usuario
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // you may need the x/y location
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                posX=(int)event.getX();
                radio=20;
                this.invalidate();
        }
        return true;
    }
    public Juego(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Definimos los objetos a pintar
        Paint fondo = new Paint();
        Paint cesta = new Paint();
        Paint banana1 = new Paint();
        Paint banana2 = new Paint();
        Paint banana3 = new Paint();
        Paint balon =new Paint();
        Paint puntos = new Paint();

        //Definimos los colores de los objetos a pintar
        fondo.setColor(Color.TRANSPARENT);
        fondo.setStyle(Paint.Style.FILL_AND_STROKE);
        cesta.setColor(Color.BLACK);
        cesta.setStyle(Paint.Style.FILL_AND_STROKE);
        banana1.setColor(Color.TRANSPARENT);
        banana1.setStyle(Paint.Style.FILL_AND_STROKE);
        banana2.setColor(Color.TRANSPARENT);
        banana2.setStyle(Paint.Style.FILL_AND_STROKE);
        banana3.setColor(Color.TRANSPARENT);
        banana3.setStyle(Paint.Style.FILL_AND_STROKE);
        balon.setColor(Color.TRANSPARENT);
        balon.setStyle(Paint.Style.FILL_AND_STROKE);
        puntos.setTextAlign(Paint.Align.CENTER);
        puntos.setTextSize(140);
        puntos.setColor(Color.rgb(224, 224,224));

        //Pinto rectángulo con un ancho y alto de 1000 o de menos si la pantalla es menor.
        canvas.drawRect(new Rect(0,0,(ancho),(alto)),fondo);

        // Pinto la pelota. La Y la implementa el timer y la X la pongo aleatoreamente en cuanto llega al final
        rectCesta= new RectF((posX-radio),(posY-radio),(posX+radio),(posY+radio));
        rectCesta2 = new RectF((posX-radio),(posY-radio),(posX+radio),(posY+radio));
        rectCesta3 = new RectF((posX-radio),(posY-radio),(posX+radio),(posY+radio));
        rectCesta4 = new RectF((posX-radio),(posY-radio),(posX+radio),(posY+radio));
        canvas.drawBitmap(bmCesta, posX, posY, cesta);

        //Pintamos banana1
        if (posMonedaY<0) {
            posMonedaY=alto;
            posMonedaX= random.nextInt(ancho);
        }
        rectMoneda = new RectF((posMonedaX-radio),(posMonedaY-radio),
                (posMonedaX+radio),(posMonedaY+radio));
        canvas.drawBitmap(bmBanana1, null, rectMoneda, null);

        //Pintamos banana2
        if (posMoneda2Y<0) {
            posMoneda2Y=alto;
            posMoneda2X= random.nextInt(ancho);
        }
        rectMoneda2 = new RectF((posMoneda2X-radio),(posMoneda2Y-radio),
                (posMoneda2X+radio),(posMoneda2Y+radio));
        canvas.drawBitmap(bmBanana2, null, rectMoneda2, null);

        //Pintamos banana3
        if (posMoneda3Y<0) {
            posMoneda3Y=alto;
            posMoneda3X= random.nextInt(ancho);
        }
        rectMoneda3 = new RectF((posMoneda3X-radio),(posMoneda3Y-radio),
                (posMoneda3X+radio),(posMoneda3Y+radio));
        canvas.drawBitmap(bmBanana3, null, rectMoneda3, null);

        //Pintamos bola
        if (posMoneda4Y<0) {
            posMoneda4Y=alto;
            posMoneda4X= random.nextInt(ancho);
        }
        rectMoneda4 = new RectF((posMoneda4X-radio),(posMoneda4Y-radio),
                (posMoneda4X+radio),(posMoneda4Y+radio));
        canvas.drawBitmap(bmBalon, null, rectMoneda4, null);


        //Si recogemos la banana1 sumamos puntuación
        if (RectF.intersects(rectCesta,rectMoneda)) {
            puntuacion += 10;
            posMonedaY = alto;
            posMonedaX = random.nextInt(ancho);
        }

        //Si recogemos la banana2 sumamos puntuación
        if (RectF.intersects(rectCesta2,rectMoneda2)) {
            puntuacion += 15;
            posMoneda2Y = alto;
            posMoneda2X = random.nextInt(ancho);
        }

        //Si recogemos la banana3 sumamos puntuación
        if (RectF.intersects(rectCesta3,rectMoneda3)) {
            puntuacion += 30;
            posMoneda3Y = alto;
            posMoneda3X = random.nextInt(ancho);
        }

        //Si recogemos la pelota restamos puntuación
        if (RectF.intersects(rectCesta4,rectMoneda4)) {
            puntuacion -= 20;
            posMoneda4Y = alto;
            posMoneda4X = random.nextInt(ancho);
        }

        //Pintamos puntuación
        canvas.drawText("SCORE: " + puntuacion.toString(), 540, 1850, puntos);

        if(puntuacion==500){
            Toast.makeText(null, "CONGRATULATIONS YOU WIN!", Toast.LENGTH_SHORT).show();
        }
    }
}
