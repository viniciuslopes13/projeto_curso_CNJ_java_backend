package br.cnj.projeto.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "CasoJudicial")
public class CasoJudicial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numero;
    private String decisao;
    private String descricao;

    @Transient
    private CustoJudicial custoJudicial;
    
    @Transient
    private TaxaJudicial taxaJudicial;

    public CasoJudicial() {
        // Construtor vazio necessário para o Spring
    }

    public CasoJudicial(int numero, String decisao, String descricao){
        this.numero = numero;
        this.decisao = decisao;
        this.descricao = descricao;
    }

    public CasoJudicial(CustoJudicial custoJudicial, TaxaJudicial taxaJudicial) {
        this.custoJudicial = custoJudicial;
        this.taxaJudicial = taxaJudicial;
    }

    public double finalizarCusto(double custo, String estado, int ano){
        custo += custoJudicial.adicionarCursot(ano);
        custo += taxaJudicial.adicionarTaxa(estado);
        return custo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDecisao() {
        return decisao;
    }

    public void setDecisao(String decisao) {
        this.decisao = decisao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
