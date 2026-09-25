package br.com.nordestech.checkout.port.input.listener;

import br.com.nordestech.checkout.infra.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CheckoutEventListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_CHECKOUT_CREATED)
    public void handleCheckoutCreatedEvent(Object checkoutDTO) {
        System.out.println("Mensagem recebida do RabbitMQ: " + checkoutDTO);
        // Chame seu caso de uso (UseCase) aqui para processar o evento
    }
}