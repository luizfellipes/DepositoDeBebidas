package org.example.service;

import org.example.enums.TipoDeMovimentacao;
import org.example.model.Bebidas;
import org.example.model.HistoricoDoDeposito;
import org.example.model.Secao;

import java.util.*;
import java.util.stream.Stream;

public class DepositoDeBebidasService {

    private List<Secao> secoes;
    private List<HistoricoDoDeposito> historicoDoDepositos;
    private static final Set<String> SECOES_PERMITIDAS = new HashSet<>(Arrays.asList("A", "B", "C", "D", "E"));

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
                    verificarSecaoPermitida(secao);
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
        return secoes.stream()
                .anyMatch(secoes -> secoes.getSecao().equalsIgnoreCase(secao));
    }

    private void verificarSecaoPermitida(Secao secao) {
        if (!SECOES_PERMITIDAS.contains(secao.getSecao().toUpperCase())) {
            throw new RuntimeException("Seção não permitida! Use apenas seções de A a E.");
        }
    }

    private boolean tiposBebidasSaoIguais(Secao secao) {
        return this.secoes.stream()
                .filter(secoes -> secoes.getSecao().equalsIgnoreCase(secao.getSecao()))
                .findFirst()
                .map(secoes -> secoes.getBebidas().getTipoDeBebida().equals(secao.getBebidas().getTipoDeBebida()))
                .orElse(true);
    }

    public double volumeTotalNaSecao(String secao) {
        return this.secoes.stream()
                .filter(secoes -> secoes.getSecao().equalsIgnoreCase(secao))
                .mapToDouble(secoes -> secoes.getBebidas().getVolume())
                .sum();
    }

    private void capacidadeSecao(Secao secao) {
        if (existeSecao(secao.getSecao()) && !tiposBebidasSaoIguais(secao)) {
            throw new RuntimeException("Não é permitido adicionar bebidas alcoólicas e não alcoólicas na mesma seção!");
        }
    }

    private void volumeExcedente(Secao secao) {
        if (volumeTotalNaSecao(secao.getSecao()) + secao.getBebidas().getVolume() > secao.capacidadeMaxima()) {
            throw new RuntimeException("Não foi possível adicionar na seção pois o volume total foi excedente!");
        }
    }

    private void entradaSaidaEstoque(Secao secao) {
        double volumeTotal = volumeTotalNaSecao(secao.getSecao());
        double volumeAtualizado = secao.getTipoDeMovimentacao().equals(TipoDeMovimentacao.ENTRADA)
                ? volumeTotal + secao.getBebidas().getVolume()
                : volumeTotal - secao.getBebidas().getVolume();

        secao.getBebidas().setVolumeTotalNaSecao(volumeAtualizado);
    }
}