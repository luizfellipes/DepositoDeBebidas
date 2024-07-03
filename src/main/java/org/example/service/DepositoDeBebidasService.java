package org.example.service;

import org.example.model.Secao;

import java.util.ArrayList;
import java.util.List;

public class DepositoDeBebidasService {

    private List<Secao> secoes;

    private void setarId(Secao secao) {
        int id = 0;
        if (secoes.isEmpty()) {
            id++;
            secao.setId(id);
        } else {
            if (secoes.getLast() != null) {
                id = secoes.getLast().getId();
                id++;
                secao.setId(id);
            }
        }
    }

    public void adicionandoNaSecao(Secao secao) {
        if (this.secoes == null) {
            this.secoes = new ArrayList<>();
        }
        capacidadeSecao(secao);
    }

    public List<Secao> todasBebidas() {
        return this.secoes;
    }

    private boolean existeSecao(String secao) {
        for (Secao secoes : secoes) {
            if (secoes.getSecao().equalsIgnoreCase(secao)) {
                return true;
            }
        }
        return false;
    }

    private boolean tiposBebidasSaoIguais(Secao secao) {
        for (Secao secoes : this.secoes) {
            if (secoes.getSecao().equalsIgnoreCase(secao.getSecao())) {
                return secoes.getBebidas().getTipoDeBebida().equals(secao.getBebidas().getTipoDeBebida());
            }
        }
        return true;
    }

    private double volumeTotalNaSecao(String secao) {
        double volumeTotal = 0.0;
        for (Secao secoes : this.secoes) {
            if (secoes.getSecao().equalsIgnoreCase(secao)) {
                volumeTotal += secoes.getBebidas().getVolume();
            }
        }
        return volumeTotal;
    }

    private void capacidadeSecao(Secao secao) {
        if (existeSecao(secao.getSecao())) {
            if (!tiposBebidasSaoIguais(secao)) {
                throw new RuntimeException("Não é permitido adicionar bebidas alcoólicas e não alcoólicas na mesma seção!");
            }

            double volumeTotal = volumeTotalNaSecao(secao.getSecao());
            if (volumeTotal + secao.getBebidas().getVolume() > secao.capacidadeMaxima()) {
                throw new RuntimeException("Não foi possível adicionar na seção pois o volume total foi excedente!");
            }
        }

        if (secao.getBebidas().getVolume() <= secao.capacidadeMaxima()) {
            setarId(secao);
            this.secoes.add(secao);
        } else {
            throw new RuntimeException("Não foi possível adicionar na seção pois o volume foi excedente!");
        }
    }
}