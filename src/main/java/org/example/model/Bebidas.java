package org.example.model;

import org.example.enums.TipoDeBebida;

public class Bebidas {

    private TipoDeBebida tipoDeBebida;
    private Double volume;


    public Bebidas() {
    }

    public Bebidas(TipoDeBebida tipoDeBebida, Double volume) {
        this.tipoDeBebida = tipoDeBebida;
        this.volume = volume;
    }

    public TipoDeBebida getTipoDeBebida() {
        return tipoDeBebida;
    }

    public Double getVolume() {
        return volume;
    }


    @Override
    public String toString() {
        return "Bebidas{" +
                "tipoDeBebida=" + tipoDeBebida +
                ", volume=" + volume +
                '}';
    }
}
