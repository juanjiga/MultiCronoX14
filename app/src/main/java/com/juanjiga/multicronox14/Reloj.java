package com.juanjiga.multicronox14;
//hola desde casa
public class Reloj {
    private int minutos;
    private int segundos;
    private int segundosTotales;
    private int porcentaje;
    private boolean onoff;
    private String colorBoton;
    private String colorNumero;
    private int segundosHoy;

    Reloj (){
        minutos = 0;
        segundos = 0;
        segundosTotales = 0;
        porcentaje = 0;
        onoff = false;
        colorBoton = "RED";
        colorNumero = "WHITE";
    }
    public void setSegundos(int segundos){
        this.segundos = segundos;
    }
    public void setMinutos(int minutos){
        this.minutos=minutos;
    }
    public int getMinutos(){
        return minutos;
    }
    public int getSegundos(){
        return segundos;
    }
    public int getSegundosTotales(){
        return segundosTotales;
    }
    public void sumaSegundos(){
        segundosTotales++;
        segundos++;
        if (segundos == 60){
            segundos = 0;
            minutos ++;
        }
    }
    public boolean getOnOff(){
        return onoff;
    }
    public void setOff(){
        this.onoff = false;
        this.colorBoton = "RED";
        this.colorNumero = "WHITE";
    }
    public void setOn(){
        this.onoff = true;
        this.colorBoton = "GREEN";
        this.colorNumero = "BLACK";
    }
    public String getColorBoton(){
        return colorBoton;
    }
    public String getColorNumero(){
        return colorNumero;
    }
    public void setPorcentaje(int porcentaje){
        this.porcentaje = porcentaje;
    }
    public int getPorcentaje(){
        return (int)porcentaje;
    }
}
