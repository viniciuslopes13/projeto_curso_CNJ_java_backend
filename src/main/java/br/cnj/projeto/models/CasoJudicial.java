package br.cnj.projeto.models;

import org.springframework.stereotype.Component;

@Component
public class CasoJudicial {
    
    private CustoJudicial custoJudicial;
    private TaxaJudicial taxaJudicial;

    public CasoJudicial(CustoJudicial custoJudicial, TaxaJudicial taxaJudicial) {
        this.custoJudicial = custoJudicial;
        this.taxaJudicial = taxaJudicial;
    }

    public double finalizarCusto(double custo, String estado, int ano){
        custo += custoJudicial.adicionarCursot(ano);
        custo += taxaJudicial.adicionarTaxa(estado);
        return custo;
    }
    
}
