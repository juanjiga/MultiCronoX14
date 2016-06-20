package com.juanjiga.multicronox14;

import android.widget.Chronometer;

public class Jugador {

    private Chronometer crono;
    private long cronoParado;

    public Jugador(ActivityCrono activityCrono) {
        crono = new Chronometer(activityCrono);
    }
    public Chronometer getCrono() {
        return crono;
    }
    public long getCronoParado() {
        return cronoParado;
    }
    public void setCronoParado(long cronoParado) {
        this.cronoParado = cronoParado;
    }
}
