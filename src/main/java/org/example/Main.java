package org.example;


import org.example.enums.TipoDeBebida;
import org.example.enums.TipoDeMovimentacao;
import org.example.model.Bebidas;
import org.example.model.Secao;
import org.example.service.DepositoDeBebidasService;

public class Main {
    public static void main(String[] args) {

        DepositoDeBebidasService depositoDeBebidasService = new DepositoDeBebidasService();

        depositoDeBebidasService.adicionandoNaSecao(new Secao("A", TipoDeMovimentacao.ENTRADA, new Bebidas(TipoDeBebida.ALCOLICO, -300.0)));
        depositoDeBebidasService.adicionandoNaSecao(new Secao("A", TipoDeMovimentacao.SAIDA, new Bebidas(TipoDeBebida.ALCOLICO, 200.0)));
        depositoDeBebidasService.todasBebidas().forEach(list -> System.out.println(list.toString()));

        System.out.println("*".repeat(10) + " Hitorico do deposito " + "*".repeat(10));
        depositoDeBebidasService.historicoDoDeposito().forEach(list -> System.out.println(list.toString()));


    }
}