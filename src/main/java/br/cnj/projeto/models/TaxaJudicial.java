package br.cnj.projeto.models;

import org.springframework.stereotype.Component;

@Component
public class TaxaJudicial {

    public double adicionarTaxa(String estado){
        if(estado.equals("DF"))
            return 10;
        return 20;
    }

}
