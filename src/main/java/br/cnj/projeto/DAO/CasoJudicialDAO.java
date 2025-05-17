package br.cnj.projeto.DAO;

import java.util.List;

import br.cnj.projeto.models.CasoJudicial;

public interface CasoJudicialDAO {
    
    CasoJudicial findById(Integer id);
    List<CasoJudicial> findAll();
    void save(CasoJudicial casoJudicial);
    void update(CasoJudicial casoJudicial);
    void partialUpdate(Integer id, CasoJudicial casoJudicial);
    void delete(Integer id);
    List<CasoJudicial> findAll(Integer page, Integer pageSize, String sortBy, String sortOrder);

}
