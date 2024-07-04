package org.example.model;

import org.example.enums.TipoDeBebida;

public class Bebidas {

    private TipoDeBebida tipoDeBebida;
    private Double volume;
    private Double volumeTotalNaSecao;


    public Bebidas() {
    }

    public Bebidas(TipoDeBebida tipoDeBebida, Double volume) {
        this.tipoDeBebida = tipoDeBebida;
        this.volume = volume;
        this.volumeTotalNaSecao = 0.0;
    }

    public TipoDeBebida getTipoDeBebida() {
        return tipoDeBebida;
    }

    public Double getVolume() {
        return volume;
    }

    public Double getVolumeTotalNaSecao() {
        return volumeTotalNaSecao;
    }

    public void setVolumeTotalNaSecao(Double volumeTotalNaSecao) {
        this.volumeTotalNaSecao = volumeTotalNaSecao;
    }

    @Override
    public String toString() {
        return "Bebidas{" +
                "tipoDeBebida=" + tipoDeBebida +
                ", volume=" + volume +
                ", volumeTotalNaSecao=" + volumeTotalNaSecao +
                '}';
    }


}
