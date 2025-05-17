package br.cnj.projeto.DTO;

public class CasoJudicialDTO {
    
    private int numero;
    private String decisao;
    private String descricao;

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
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
