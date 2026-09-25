package br.com.nordestech.checkout.infra.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String CHECKOUT_EXCHANGE = "checkout.v1.events";
    public static final String QUEUE_CHECKOUT_CREATED = "checkout.created.queue";
    public static final String ROUTING_KEY_CHECKOUT_CREATED = "checkout.created";

    // 1. Fila que armazenará as mensagens
    @Bean
    public Queue checkoutCreatedQueue() {
        return QueueBuilder.durable(QUEUE_CHECKOUT_CREATED)
                .build();
    }

    // 2. Exchange do tipo Direct
    @Bean
    public DirectExchange checkoutExchange() {
        return new DirectExchange(CHECKOUT_EXCHANGE);
    }

    // 3. Vinculo (Binding) entre a Fila e a Exchange através de uma Routing Key
    @Bean
    public Binding checkoutCreatedBinding(Queue checkoutCreatedQueue, DirectExchange checkoutExchange) {
        return BindingBuilder.bind(checkoutCreatedQueue)
                .to(checkoutExchange)
                .with(ROUTING_KEY_CHECKOUT_CREATED);
    }

    // 4. Conversor para enviar/receber objetos em formato JSON (Jackson)
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}