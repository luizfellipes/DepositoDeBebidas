package org.example.service;

import org.example.enums.TipoDeMovimentacao;
import org.example.model.Bebidas;
import org.example.model.HistoricoDoDeposito;
import org.example.model.Secao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DepositoDeBebidasService {

    private List<Secao> secoes;
    private List<HistoricoDoDeposito> historicoDoDepositos;

    private void setarId(Secao secao) {
        secao.setId(secoes.isEmpty() ? 1 : secoes.getLast().getId() + 1);
    }

    private void iniciaList() {
        if (this.secoes == null) {
            this.secoes = new ArrayList<>();
            this.historicoDoDepositos = new ArrayList<>();
        }
    }

    public void adicionandoNaSecao(Secao secao) {
        Stream.of(secao)
                .filter(secao1 -> secao1.getBebidas().getVolume() <= secao1.capacidadeMaxima())
                .map(secao1 -> {
                    iniciaList();
                    capacidadeSecao(secao);
                    volumeExcedente(secao);
                    setarId(secao);
                    entradaSaidaEstoque(secao);
                    criaHistorico(secao);
                    return this.secoes.add(secao);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Não foi possível adicionar na seção pois o volume foi excedente!"));
    }

    private void criaHistorico(Secao secao) {
        this.historicoDoDepositos.add(new HistoricoDoDeposito(secao.getId(), secao.getSecao(), secao.getTipoDeMovimentacao(),
                new Bebidas(secao.getBebidas().getTipoDeBebida(), secao.getBebidas().getVolume())));
    }

    public List<Secao> todasBebidas() {
        return this.secoes;
    }

    public List<HistoricoDoDeposito> historicoDoDeposito() {
        return this.historicoDoDepositos;
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

    public double volumeTotalNaSecao(String secao) {
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
        }
    }

    private void volumeExcedente(Secao secao) {
        if (volumeTotalNaSecao(secao.getSecao()) + secao.getBebidas().getVolume() > secao.capacidadeMaxima()) {
            throw new RuntimeException("Não foi possível adicionar na seção pois o volume total foi excedente!");
        }
    }

    private void entradaSaidaEstoque(Secao secao) {
        double volumeTotal = volumeTotalNaSecao(secao.getSecao());
        double volumeAtualizado;

        if (secao.getTipoDeMovimentacao().equals(TipoDeMovimentacao.ENTRADA)) {
            volumeAtualizado = volumeTotal + secao.getBebidas().getVolume();
        } else {
            volumeAtualizado = volumeTotal - secao.getBebidas().getVolume();
        }

        secao.getBebidas().setVolumeTotalNaSecao(volumeAtualizado);
    }
}