package com.juanjiga.multicronox14;

//MultiCrono para 14 jugadores

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import java.util.TimerTask;
import java.util.Timer;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityCrono extends AppCompatActivity implements OnClickListener {

    Reloj[] relojes = new Reloj[15];
    String[] relojeS = new String[15];
    //Jugador[] jugador = new Jugador[15];
    int segundosTotales;
    boolean contando;
    Timer tiempo;
    PowerManager.WakeLock wakelock;
    Button botonTiempo;
    Button[] boton = new Button[15];

    //Button boton1, boton2, boton3, boton4, boton5;
    //Button boton6, boton7, boton8, boton9, boton10;
    //Button boton11, boton12, boton13, boton14;

    Button botonStart, botonPausa, botonContinuar;

    SQLiteDatabase db;
    DbHelper dbHelper;

    TimerTask tarea = new TimerTask() {
        public void run() {
            mensaje.sendEmptyMessage(0);
        }
    };
    Handler mensaje = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            actualizaTiempo();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cronos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakelock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "cronoculto");
        wakelock.setReferenceCounted(false);
        if (!wakelock.isHeld()) {
            wakelock.acquire();
        }

        DbHelper dbHelper = new DbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        contando = false;
        segundosTotales = 0;
        for (int i = 0; i < 15; i++) {
            relojes[i] = new Reloj();
            relojeS[i] = "00:00";
            //jugador[i] = new Jugador(this);
        }
        boton[0] = (Button) findViewById(R.id.botonTiempo);
        boton[1] = (Button) findViewById(R.id.boton1);
        boton[2] = (Button) findViewById(R.id.boton2);
        boton[3] = (Button) findViewById(R.id.boton3);
        boton[4] = (Button) findViewById(R.id.boton4);
        boton[5] = (Button) findViewById(R.id.boton5);
        boton[6] = (Button) findViewById(R.id.boton6);
        boton[7] = (Button) findViewById(R.id.boton7);
        boton[8] = (Button) findViewById(R.id.boton8);
        boton[9] = (Button) findViewById(R.id.boton9);
        boton[10] = (Button) findViewById(R.id.boton10);
        boton[11] = (Button) findViewById(R.id.boton11);
        boton[12] = (Button) findViewById(R.id.boton12);
        boton[13] = (Button) findViewById(R.id.boton13);
        boton[14] = (Button) findViewById(R.id.boton14);
        botonStart = (Button) findViewById(R.id.botonStart);
        botonPausa = (Button) findViewById(R.id.botonPausa);
        botonPausa.setVisibility(View.INVISIBLE);
        botonContinuar = (Button) findViewById(R.id.botonContinuar);
        botonContinuar.setVisibility(View.INVISIBLE);
        /*botonTiempo.setOnClickListener(this);
        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
        boton4.setOnClickListener(this);
        boton5.setOnClickListener(this);
        boton6.setOnClickListener(this);
        boton7.setOnClickListener(this);
        boton8.setOnClickListener(this);
        boton9.setOnClickListener(this);
        boton10.setOnClickListener(this);
        boton11.setOnClickListener(this);
        boton12.setOnClickListener(this);
        boton13.setOnClickListener(this);
        boton14.setOnClickListener(this);*/
        for (int j=1; j<15; j++){
            boton[j].setOnClickListener(this);
        }
        botonStart.setOnClickListener(this);
        botonPausa.setOnClickListener(this);
        botonContinuar.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        for (int i=1; i<15; i++){
            if (v == boton[i]){
                cambiaOnOff(i);
                boton[i].setBackgroundColor(Color.parseColor(relojes[i].getColorBoton()));
                boton[i].setTextColor(Color.parseColor(relojes[i].getColorNumero()));
            }
        }
        /*switch (v.getId()) {
            case R.id.boton1:
                cambiaOnOff(1);
                boton[1].setBackgroundColor(Color.parseColor(relojes[1].getColorBoton()));
                boton[1].setTextColor(Color.parseColor(relojes[1].getColorNumero()));
                //jugador[1].getCrono().start();
                break;
            case R.id.boton2:
                cambiaOnOff(2);
                boton2.setBackgroundColor(Color.parseColor(relojes[2].getColorBoton()));
                boton2.setTextColor(Color.parseColor(relojes[2].getColorNumero()));
                break;
            case R.id.boton3:
                cambiaOnOff(3);
                boton3.setBackgroundColor(Color.parseColor(relojes[3].getColorBoton()));
                boton3.setTextColor(Color.parseColor(relojes[3].getColorNumero()));
                break;
            case R.id.boton4:
                cambiaOnOff(4);
                boton4.setBackgroundColor(Color.parseColor(relojes[4].getColorBoton()));
                boton4.setTextColor(Color.parseColor(relojes[4].getColorNumero()));
                break;
            case R.id.boton5:
                cambiaOnOff(5);
                boton5.setBackgroundColor(Color.parseColor(relojes[5].getColorBoton()));
                boton5.setTextColor(Color.parseColor(relojes[5].getColorNumero()));
                break;
            case R.id.boton6:
                cambiaOnOff(6);
                boton6.setBackgroundColor(Color.parseColor(relojes[6].getColorBoton()));
                boton6.setTextColor(Color.parseColor(relojes[6].getColorNumero()));
                break;
            case R.id.boton7:
                cambiaOnOff(7);
                boton7.setBackgroundColor(Color.parseColor(relojes[7].getColorBoton()));
                boton7.setTextColor(Color.parseColor(relojes[7].getColorNumero()));
                break;
            case R.id.boton8:
                cambiaOnOff(8);
                boton8.setBackgroundColor(Color.parseColor(relojes[8].getColorBoton()));
                boton8.setTextColor(Color.parseColor(relojes[8].getColorNumero()));
                break;
            case R.id.boton9:
                cambiaOnOff(9);
                boton9.setBackgroundColor(Color.parseColor(relojes[9].getColorBoton()));
                boton9.setTextColor(Color.parseColor(relojes[9].getColorNumero()));
                break;
            case R.id.boton10:
                cambiaOnOff(10);
                boton10.setBackgroundColor(Color.parseColor(relojes[10].getColorBoton()));
                boton10.setTextColor(Color.parseColor(relojes[10].getColorNumero()));
                break;
            case R.id.boton11:
                cambiaOnOff(11);
                boton11.setBackgroundColor(Color.parseColor(relojes[11].getColorBoton()));
                boton11.setTextColor(Color.parseColor(relojes[11].getColorNumero()));
                break;
            case R.id.boton12:
                cambiaOnOff(12);
                boton12.setBackgroundColor(Color.parseColor(relojes[12].getColorBoton()));
                boton12.setTextColor(Color.parseColor(relojes[12].getColorNumero()));
                break;
            case R.id.boton13:
                cambiaOnOff(13);
                boton13.setBackgroundColor(Color.parseColor(relojes[13].getColorBoton()));
                boton13.setTextColor(Color.parseColor(relojes[13].getColorNumero()));
                break;
            case R.id.boton14:
                cambiaOnOff(14);
                boton14.setBackgroundColor(Color.parseColor(relojes[14].getColorBoton()));
                boton14.setTextColor(Color.parseColor(relojes[14].getColorNumero()));
                break;*/
        switch (v.getId()) {
            case R.id.botonStart:
                tiempo = new Timer();
                tiempo.schedule(tarea,100,1000);
                relojes[0].setOn();
                contando = true;
                botonStart.setVisibility(View.INVISIBLE);
                botonPausa.setVisibility(View.VISIBLE);
                prepararCronos();
                break;
            case R.id.botonPausa:
                relojes[0].setOff();
                contando = false;
                botonPausa.setVisibility(View.INVISIBLE);
                botonContinuar.setVisibility(View.VISIBLE);
                break;
            case R.id.botonContinuar:
                relojes[0].setOn();
                contando = true;
                botonContinuar.setVisibility(View.INVISIBLE);
                botonPausa.setVisibility(View.VISIBLE);
                break;
        }
    }
    public void cambiaOnOff(int i) {
        if (relojes[i].getOnOff())
            relojes[i].setOff();
        else relojes[i].setOn();
    }

    public void prepararCronos(){
        for (int i=1; i<15; i++){
            boton[i].setBackgroundColor(Color.parseColor(relojes[i].getColorBoton()));
            boton[i].setTextColor(Color.parseColor(relojes[i].getColorNumero()));
        }
        /*boton1.setBackgroundColor(Color.parseColor(relojes[1].getColorBoton()));
        boton1.setTextColor(Color.parseColor(relojes[1].getColorNumero()));
        boton2.setBackgroundColor(Color.parseColor(relojes[2].getColorBoton()));
        boton2.setTextColor(Color.parseColor(relojes[2].getColorNumero()));
        boton3.setBackgroundColor(Color.parseColor(relojes[3].getColorBoton()));
        boton3.setTextColor(Color.parseColor(relojes[3].getColorNumero()));
        boton4.setBackgroundColor(Color.parseColor(relojes[4].getColorBoton()));
        boton4.setTextColor(Color.parseColor(relojes[4].getColorNumero()));
        boton5.setBackgroundColor(Color.parseColor(relojes[5].getColorBoton()));
        boton5.setTextColor(Color.parseColor(relojes[5].getColorNumero()));
        boton6.setBackgroundColor(Color.parseColor(relojes[6].getColorBoton()));
        boton6.setTextColor(Color.parseColor(relojes[6].getColorNumero()));
        boton7.setBackgroundColor(Color.parseColor(relojes[7].getColorBoton()));
        boton7.setTextColor(Color.parseColor(relojes[7].getColorNumero()));
        boton8.setBackgroundColor(Color.parseColor(relojes[8].getColorBoton()));
        boton8.setTextColor(Color.parseColor(relojes[8].getColorNumero()));
        boton9.setBackgroundColor(Color.parseColor(relojes[9].getColorBoton()));
        boton9.setTextColor(Color.parseColor(relojes[9].getColorNumero()));
        boton10.setBackgroundColor(Color.parseColor(relojes[10].getColorBoton()));
        boton10.setTextColor(Color.parseColor(relojes[10].getColorNumero()));
        boton11.setBackgroundColor(Color.parseColor(relojes[11].getColorBoton()));
        boton11.setTextColor(Color.parseColor(relojes[11].getColorNumero()));
        boton12.setBackgroundColor(Color.parseColor(relojes[12].getColorBoton()));
        boton12.setTextColor(Color.parseColor(relojes[12].getColorNumero()));
        boton13.setBackgroundColor(Color.parseColor(relojes[13].getColorBoton()));
        boton13.setTextColor(Color.parseColor(relojes[13].getColorNumero()));
        boton14.setBackgroundColor(Color.parseColor(relojes[14].getColorBoton()));
        boton14.setTextColor(Color.parseColor(relojes[14].getColorNumero()));*/
    }
    public void actualizaTiempo() {
        if (contando) {
            segundosTotales++;
            for (int i = 0; i < 15; i++) {
                if (relojes[i].getOnOff()) {
                    relojes[i].sumaSegundos();
                }
                relojes[i].setPorcentaje(relojes[i].getSegundosTotales() * 100 / segundosTotales);
                if (relojes[i].getMinutos() < 10 && relojes[i].getSegundos() < 10)
                    relojeS[i] = "0" + relojes[i].getMinutos() + ":0" + relojes[i].getSegundos() + " " + relojes[i].getPorcentaje() + "%";
                else if (relojes[i].getMinutos() < 10)
                    relojeS[i] = "0" + relojes[i].getMinutos() + ":" + relojes[i].getSegundos() + " " + relojes[i].getPorcentaje() + "%";
                else if (relojes[i].getSegundos() < 10)
                    relojeS[i] = relojes[i].getMinutos() + ":0" + relojes[i].getSegundos() + " " + relojes[i].getPorcentaje() + "%";
                else
                    relojeS[i] = relojes[i].getMinutos() + ":" + relojes[i].getSegundos() + " " + relojes[i].getPorcentaje() + "%";
            }
            for (int i=0; i<15; i++) {
                boton[i].setText(relojeS[i]);
            }
            /*botonTiempo.setText(relojeS[0]);
            boton1.setText(relojeS[1]);
            boton2.setText(relojeS[2]);
            boton3.setText(relojeS[3]);
            boton4.setText(relojeS[4]);
            boton5.setText(relojeS[5]);
            boton6.setText(relojeS[6]);
            boton7.setText(relojeS[7]);
            boton8.setText(relojeS[8]);
            boton9.setText(relojeS[9]);
            boton10.setText(relojeS[10]);
            boton11.setText(relojeS[11]);
            boton12.setText(relojeS[12]);
            boton13.setText(relojeS[13]);
            boton14.setText(relojeS[14]);*/
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                builder.setTitle("¡¡ Atención !!");
                builder.setMessage("  ¿ Salir ?  ");
                builder.setCancelable(true);
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface builder, int id) {
                        //for (int i=1; i<15; i++) {
                            //dbHelper.modificar(i, relojes[i].getSegundosTotales());
                        //}
                        wakelock.release();
                        System.exit(RESULT_OK);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface builder, int id) {
                        builder.cancel();
                    }
                });
                builder.show();
                break;
        }
        return super.onKeyDown(keyCode, event);

    }
}
