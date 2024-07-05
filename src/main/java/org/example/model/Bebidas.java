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
        this.volume = validaValoresNegativos(volume);
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

    private Double validaValoresNegativos(Double volume) {
        if (volume <= 0) {
           throw new RuntimeException("Não é permitido volumes a baixo de 0");
        } else {
           return this.volume = volume;
        }
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
