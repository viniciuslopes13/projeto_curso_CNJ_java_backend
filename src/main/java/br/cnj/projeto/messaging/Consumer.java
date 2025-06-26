package br.cnj.projeto.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.cnj.projeto.config.QueueConfig;
import br.cnj.projeto.models.CasoJudicial;

@Component
public class Consumer {

    @RabbitListener(queues = {QueueConfig.NOME_FILA})
    public void receive(@Payload String fileBody){
        System.out.println("################### RECEBI ESSA MENSAGEM: "+ fileBody);
    }

    // @RabbitListener(queues = {QueueConfig.NOME_FILA})
    // public void receive(@Payload CasoJudicial fileBody){
    //     System.out.println("################### Caso Judicial Recebido: "+ fileBody.getNumero()+" - "+fileBody.getDescricao());
    // }

}
