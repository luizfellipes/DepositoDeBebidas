package org.example.model;

import org.example.enums.TipoDeMovimentacao;


public class HistoricoDoDeposito extends Secao {

    public HistoricoDoDeposito(Integer id, String secao, TipoDeMovimentacao tipoDeMovimentacao, Bebidas bebidas) {
        super(id, secao, tipoDeMovimentacao, bebidas);
        capacidadeMaxima();
        getBebidas().getVolumeTotalNaSecao();
    }


}
