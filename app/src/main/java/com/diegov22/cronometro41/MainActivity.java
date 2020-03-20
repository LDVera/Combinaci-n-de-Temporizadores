package com.diegov22.cronometro41;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.*;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    TextView Vista1, Vista2, Vista3;
    int time = 0;
    int rate = 100;
    Timer timerN1, timerN2, timerN3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Vista1 =(TextView) findViewById(R.id.Primer_Cro);
        Vista2 =(TextView) findViewById(R.id.Segundo_Cro);
        Vista3 =(TextView) findViewById(R.id.Tercer_Cro);

        int factor1 = 1;
        int factor2 = 2;
        int factor3 = 3;

        timerN1  =  new Timer("Temporizador");
        Tarea tarea = new Tarea (Vista1, factor1);
        timerN1.scheduleAtFixedRate(tarea, 0, rate);

        timerN2  =  new Timer("Temporizador");
        Tarea2 tarea2 = new Tarea2 (Vista2, factor2);
        timerN2.scheduleAtFixedRate(tarea2, 0, rate);

        timerN3  =  new Timer("Temporizador");
        Tarea3 tarea3 = new Tarea3 (Vista3, factor3);
        timerN3.scheduleAtFixedRate(tarea3, 0, rate);


    }
    @Override
    public void onPause(){
        super.onPause();
        timerN1.cancel();
        timerN2.cancel();
        timerN3.cancel();
    }
    class Tarea extends TimerTask{
        int factor;
        TextView textTarea;
        public  Tarea(TextView textView, int fact){
            textTarea=textView;
            factor=fact;
        }
        @Override
        public  void run(){
            Runnable cambiaTexto = new CambiaTexto(textTarea, factor);
            runOnUiThread(cambiaTexto);
        }
    }

    class Tarea2 extends TimerTask{
        int factor;
        TextView textTarea;
        public  Tarea2 (TextView textView, int fact){
            textTarea=textView;
            factor=fact;
        }
        @Override
        public  void run(){
            Runnable cambiaTexto = new CambiaTexto(textTarea, factor);
            textTarea.post(cambiaTexto);
        }
    }

    class Tarea3 extends TimerTask{
        int factor;
        TextView textTarea;
        public  Tarea3 (TextView textView, int fact){
            textTarea=textView;
            factor=fact;
        }
        @Override
        public  void run(){
            Runnable cambiaTexto = new CambiaTexto(textTarea, factor);
            handler.post(cambiaTexto);
        }
    }



    class CambiaTexto implements Runnable{
        int red, green, blue, factor;
        TextView textCambia;
        public  CambiaTexto (TextView textView, int fact){
            textCambia=textView;
            factor = fact;
        }
        @Override
        public void run(){
            time=time+rate;
            red  =(time/factor)%255;
            green=(int) ((0.75* time/factor)%255);
            blue=(int) ((0.60* time/factor)%255);

            String texto = "TEMPORIZADOR\n rate="+ rate + "\n t= "+ time;
            textCambia.setText(texto);
            textCambia.setTypeface(null, Typeface.BOLD);
            textCambia.setTextSize(30);
            textCambia.setTextColor(Color.rgb(red, green, blue));
        }
    }

}
