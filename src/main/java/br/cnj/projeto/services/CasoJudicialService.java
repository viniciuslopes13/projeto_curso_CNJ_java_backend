package br.cnj.projeto.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.cnj.projeto.models.CasoJudicial;

@Service
public class CasoJudicialService {
    
    private List<CasoJudicial> casos;

    public CasoJudicialService() {
        casos = new ArrayList<CasoJudicial>(Arrays.asList(new CasoJudicial[]{
            new CasoJudicial(1,"A", "Caso 1"),
            new CasoJudicial(2,"I", "Caso 2"),
            new CasoJudicial(3,"I", "Caso 3"),
            new CasoJudicial(4,"A", "Caso 4"),
        }));
    }

    /**
     * @return Retorna todos os casos.
     */
    public List<CasoJudicial> pegarTodosOsCasos(){
        return casos;
    }

    /**
     * @param id
     * @return Retorna um caso a partir do id informado.
     */
    public CasoJudicial pegarCasoPorId(int id){
        for(CasoJudicial caso : casos){
            if (caso.getNumero() == id) {
                return caso;
            }
        }
        return null;
    }

    /**
     * @param caso
     * @return Insere um caso judicial passado por parâmetro e retorna o caso.
     */
    public CasoJudicial insereCasoJudicial(CasoJudicial caso){
        int id = casos.getLast().getNumero() + 1;
        caso.setNumero(id);
        casos.add(caso);
        return caso;
    }

    /**
     * @param id
     * Apaga um caso judicial conforme id passado por parâmetro.
     */
    public void removeCasoJudicial(int id){
        casos.removeIf(caso -> caso.getNumero() == id);
    }

    /**
     * @param casoJudicial
     * @return Atualiza um caso judicial. Remove o caso e adiciona um novo com as novas informações.
     */
    public CasoJudicial atualizaCasoJudicial(Long id, CasoJudicial casoJudicial){
        CasoJudicial caso = pegarCasoPorId(id.intValue());
        if(caso != null){
            casos.remove(caso);
            caso = casoJudicial;
            caso.setNumero(id.intValue());
            casos.add(caso);
            return caso;
        }
        return null;
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
