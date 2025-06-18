package br.cnj.projeto.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.cnj.projeto.DAO.CasoJudicialDAO;
import br.cnj.projeto.models.CasoJudicial;

@Service
public class CasoJudicialService {
    
    private final CasoJudicialDAO casoJudicialDAO;
    
    public CasoJudicialService(CasoJudicialDAO casoJudicialDAO){
        this.casoJudicialDAO = casoJudicialDAO;
    }

    /**
     * @return Retorna todos os casos.
     */
    public List<CasoJudicial> pegarTodosOsCasos(){
        return casoJudicialDAO.findAll();
    }

    /**
     * @param id
     * @return Retorna um caso a partir do id informado.
     */
    public CasoJudicial pegarCasoPorId(int id){
        return casoJudicialDAO.findById(id);
    }

    /**
     * @param caso
     * @return Insere um caso judicial passado por parâmetro e retorna o caso.
     */
    public CasoJudicial insereCasoJudicial(CasoJudicial caso){
        casoJudicialDAO.save(caso);        
        return caso;
    }

    /**
     * @param id
     * Apaga um caso judicial conforme id passado por parâmetro.
     */
    public void removeCasoJudicial(int id){
        casoJudicialDAO.delete(id);
    }


    /**
     * @param casoJudicial
     * Atualiza um caso judicial. Faz um merge de todas as informações
     */
    public void update(CasoJudicial casoJudicial){
        casoJudicialDAO.update(casoJudicial);
    }

    /**
     * @param casoJudicial
     * @return Atualiza um caso judicial. Aqui atualiza somente as novas informações.
     */
    public CasoJudicial atualizaCasoJudicial(Long id, CasoJudicial casoJudicial){
        casoJudicialDAO.partialUpdate(id.intValue(), casoJudicial);
        return casoJudicial;
    }

    /**
     * @param id 
     * @param atualizacoes
     * @return Atualiza um caso judicial de acordo com o id passado por parâmetro. Aqui é atualizado exatamente os campos
     * que são passados no body, o caso não é apagado e adicionado novamente como em atualizaCasoJudicial().
     */
    public boolean atualizarCaso(Long id, Map<String, Object> atualizacoes){
        CasoJudicial caso = pegarCasoPorId(id.intValue());

        for(Map.Entry<String, Object> entry : atualizacoes.entrySet()){
            String atributo = entry.getKey();
            Object valor = entry.getValue();

            try{
                Field field = caso.getClass().getDeclaredField(atributo); // Obtém o campo
                field.setAccessible(true); // Torna o campo acessível
                field.set(caso, valor); // Define o novo valor no objeto
            }catch(NoSuchFieldException | IllegalAccessException e){
                System.err.println(e.getMessage());
                return false;
            }
        }
        
        return true;
    }
    
}
