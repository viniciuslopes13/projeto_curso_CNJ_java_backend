package br.cnj.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.cnj.projeto.models.CasoJudicial;

@Repository
public interface CasoJudicialRepository extends JpaRepository<CasoJudicial, Integer>{
    CasoJudicial findByNumero(Integer numero);
}
