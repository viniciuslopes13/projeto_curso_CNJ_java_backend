package br.cnj.projeto.controllers;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cnj.projeto.models.CasoJudicial;
import br.cnj.projeto.services.CasoJudicialService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/api/casos")
public class CasoJudicialController {
    
    private final CasoJudicialService casoJudicialService;
    private static final Logger logger = (Logger) LogManager.getLogger(CasoJudicialController.class);

    public CasoJudicialController(CasoJudicialService service){
        this.casoJudicialService = service;
    }
    
    @GetMapping
    public ResponseEntity<List<CasoJudicial>> pegarTodosOsCasos(){
        logger.info("Recuperando todos os casos!");
        return ResponseEntity.ok(casoJudicialService.pegarTodosOsCasos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CasoJudicial> pegarCasoPorId(@PathVariable Long id){
        return ResponseEntity.ofNullable(casoJudicialService.pegarCasoPorId(id.intValue()));
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<CasoJudicial> getCasoJudicialId(@PathVariable Long id){
        CasoJudicial casoJudicial = casoJudicialService.pegarCasoPorId(id.intValue());
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CasoJudicialController.class).getCasoJudicialId(id)).withSelfRel();
        Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CasoJudicialController.class).atualizaCasoJudicial(id, casoJudicial)).withRel("update");
        Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CasoJudicialController.class).apagaCasoJudicial(id)).withRel("delete");
        EntityModel<CasoJudicial> resource = EntityModel.of(casoJudicial);
        resource.add(selfLink,updateLink,deleteLink);
        return resource;
    }

    @PostMapping
    public ResponseEntity<CasoJudicial> insereCasoJudicial(@RequestBody CasoJudicial novoCaso){
        casoJudicialService.insereCasoJudicial(novoCaso);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(novoCaso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CasoJudicial> atualizaCasoJudicial(@PathVariable Long id, @RequestBody CasoJudicial casoAtualizado){
        casoJudicialService.atualizaCasoJudicial(id, casoAtualizado);
        return ResponseEntity.ofNullable(casoAtualizado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> ajustarCaso(@PathVariable Long id, @RequestBody Map<String, Object> atualizacoes){
        if(casoJudicialService.atualizarCaso(id, atualizacoes)){
            return ResponseEntity.ok("Sucesseo ao atualizar o caso "+id);
        }else{
            return ResponseEntity.badRequest().body("Erro ao atualizar o caso "+id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagaCasoJudicial(@PathVariable Long id){
        casoJudicialService.removeCasoJudicial(id.intValue());
        return ResponseEntity.noContent().build();
    }

}
