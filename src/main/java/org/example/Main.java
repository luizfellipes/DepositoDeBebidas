package org.example;


import org.example.enums.TipoDeBebida;
import org.example.enums.TipoDeMovimentacao;
import org.example.model.Bebidas;
import org.example.model.Secao;
import org.example.service.DepositoDeBebidasService;

public class Main {
    public static void main(String[] args) {

        DepositoDeBebidasService depositoDeBebidasService = new DepositoDeBebidasService();

        depositoDeBebidasService.adicionandoNaSecao(new Secao(1, "A", TipoDeMovimentacao.ENTRADA, new Bebidas(TipoDeBebida.ALCOLICO, 200.0)));
        depositoDeBebidasService.adicionandoNaSecao(new Secao(1, "A", TipoDeMovimentacao.ENTRADA, new Bebidas(TipoDeBebida.NAOALCOLICO, 200.0)));
        depositoDeBebidasService.todasBebidas().forEach(list -> System.out.println(list.toString()));

    }
}