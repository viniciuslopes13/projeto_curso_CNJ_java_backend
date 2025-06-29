package br.cnj.projeto.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    @Value(QueueConfig.NOME_FILA)
    private String message;

    public Queue producerQueue(){
        return new Queue(message, true);
    }

}
