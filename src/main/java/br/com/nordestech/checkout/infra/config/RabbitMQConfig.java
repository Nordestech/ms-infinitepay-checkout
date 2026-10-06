package br.com.nordestech.checkout.infra.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PAYMENT_APPROVED_EXCHANGE = "payment.v1.events";
    public static final String QUEUE_PAYMENT_APPROVED = "payment.approved.queue";
    public static final String ROUTING_KEY_PAYMENT_APPROVED = "payment.approved";

    // 1. Fila que armazenará as mensagens
    @Bean
    public Queue paymentApprovedQueue() {
        return QueueBuilder.durable(QUEUE_PAYMENT_APPROVED)
                .build();
    }

    // 2. Exchange do tipo Direct
    @Bean
    public DirectExchange paymentApprovedExchange() {
        return new DirectExchange(PAYMENT_APPROVED_EXCHANGE);
    }

    // 3. Vinculo (Binding) entre a Fila e a Exchange através de uma Routing Key
    @Bean
    public Binding paymentApprovedBinding(Queue paymentApprovedQueue, DirectExchange paymentApprovedExchange) {
        return BindingBuilder.bind(paymentApprovedQueue)
                .to(paymentApprovedExchange)
                .with(ROUTING_KEY_PAYMENT_APPROVED);
    }

    // 4. Conversor para enviar/receber objetos em formato JSON (Jackson)
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}