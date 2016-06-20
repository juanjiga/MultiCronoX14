package com.juanjiga.multicronox14;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptador extends BaseAdapter {

    private Context context;
    private ArrayList<?> entrada;

    public Adaptador(Context context, ArrayList<?> entrada) {
        super();
        this.context = context;
        this.entrada = entrada;
    }
    @Override
    public int getCount() {
        return this.entrada.size();
    }
    @Override
    public Object getItem(int position) {
        return this.entrada.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.casilla, parent, false);
        }
        // Set data into the view.
        ImageView imagen = (ImageView) rowView.findViewById(R.id.imagen);
        TextView texto1 = (TextView) rowView.findViewById(R.id.texto1);
        TextView texto2 = (TextView) rowView.findViewById(R.id.texto2);

        return rowView;
    }

}
