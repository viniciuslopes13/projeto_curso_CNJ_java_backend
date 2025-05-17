package br.cnj.projeto.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.cnj.projeto.models.CasoJudicial;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CasoJudicialDAOImpl implements CasoJudicialDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CasoJudicial findById(Integer id) {
        return entityManager.find(CasoJudicial.class, id);    
    }

    @Override
    public List<CasoJudicial> findAll() {
        return entityManager.createQuery("SELECT c FROM CasoJudicial c", CasoJudicial.class).getResultList();       
    }

    @Override
    public void save(CasoJudicial casoJudicial) {
        entityManager.persist(casoJudicial);    
    }

    @Override
    public void update(CasoJudicial casoJudicial) {
        entityManager.merge(casoJudicial);
    }

    @Override
    public void partialUpdate(Integer id, CasoJudicial casoJudicial) {
        CasoJudicial existingCasoJudicial =  findById(id);
        if(existingCasoJudicial != null){
            if (casoJudicial.getDescricao() != null) {
                existingCasoJudicial.setDescricao(casoJudicial.getDescricao());
            }
            if (casoJudicial.getDecisao() != null) {
                existingCasoJudicial.setDecisao(casoJudicial.getDecisao());
            }
            entityManager.merge(existingCasoJudicial);
        }    
    }

    @Override
    public void delete(Integer id) {
        CasoJudicial casoJudicial = findById(id);
        if (casoJudicial != null) {
            entityManager.remove(casoJudicial);
        }
    }

    @Override
    public List<CasoJudicial> findAll(Integer page, Integer pageSize, String sortBy, String sortOrder) {
        Integer offset = (page-1) * pageSize;
        String query = "SELECT c FROM CasoJudicial c ORDER BY c."+sortBy+" "+sortOrder;
        return entityManager.createQuery(query, CasoJudicial.class)
                .setFirstResult(offset)
                .setMaxResults(pageSize)
                .getResultList();
    }
    
}
