package com.juanjiga.multicronox14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botonCrono, botonDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonCrono = (Button) findViewById(R.id.botonCrono);
        botonDatos = (Button) findViewById(R.id.botonDatos);
        botonCrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent nueva = new Intent(getApplicationContext(), ActivityCrono.class);
               startActivity(nueva);
            }
        });
        botonDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent nueva = new Intent(getApplicationContext(), ActivityListado.class);
               startActivity(nueva);
            }
        });
        Toast.makeText(getBaseContext(),"HOLAAAA!!!! ... Que tal?", Toast.LENGTH_SHORT).show();
    }

}
