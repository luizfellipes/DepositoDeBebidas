package org.example.model;

import org.example.enums.TipoDeBebida;
import org.example.enums.TipoDeMovimentacao;


public class Secao {


    private Integer id;
    private Double capacidadeMaxima;
    private String secao;
    private Bebidas bebidas;
    private TipoDeMovimentacao tipoDeMovimentacao;


    public Secao(String secao, TipoDeMovimentacao tipoDeMovimentacao, Bebidas bebidas) {
        this.secao = secao;
        this.bebidas = bebidas;
        this.tipoDeMovimentacao = tipoDeMovimentacao;
    }

    public Secao(Integer id, String secao, TipoDeMovimentacao tipoDeMovimentacao, Bebidas bebidas) {
        this.id = id;
        this.secao = secao;
        this.bebidas = bebidas;
        this.tipoDeMovimentacao = tipoDeMovimentacao;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public String getSecao() {
        return secao;
    }

    public Bebidas getBebidas() {
        return bebidas;
    }

    public TipoDeMovimentacao getTipoDeMovimentacao() {
        return tipoDeMovimentacao;
    }

    public Double capacidadeMaxima() {
        if (bebidas.getTipoDeBebida().equals(TipoDeBebida.ALCOLICO)) {
            return this.capacidadeMaxima = 500.0;
        } else {
            return this.capacidadeMaxima = 400.0;
        }
    }

    @Override
    public String toString() {
        return "Secao{" +
                "id=" + id +
                ", capacidadeMaxima=" + capacidadeMaxima +
                ", secao='" + secao + '\'' +
                ", bebidas=" + bebidas +
                '}';
    }
}
