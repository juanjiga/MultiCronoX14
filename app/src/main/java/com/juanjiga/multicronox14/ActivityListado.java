package com.juanjiga.multicronox14;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ActivityListado extends Activity {

    ListView listado;
    ArrayList<Reloj> datos = new ArrayList<Reloj>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listado);

        listado = (ListView) findViewById(R.id.listado);

        listado.setAdapter(new Adaptador(this, datos));

        datos.add(new Reloj());

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int posicion, long arg) {

            }
        });

    }

}
