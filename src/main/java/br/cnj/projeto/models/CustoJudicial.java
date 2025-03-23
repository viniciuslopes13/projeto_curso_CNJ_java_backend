package br.cnj.projeto.models;

import org.springframework.stereotype.Component;

@Component
public class CustoJudicial {

    public double adicionarCursot(int ano){
        if(ano>=2024)
            return 20;
        return 10;
    }

}
