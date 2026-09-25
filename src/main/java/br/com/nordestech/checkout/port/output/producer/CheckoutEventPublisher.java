package br.com.nordestech.checkout.port.output.producer;

import br.com.nordestech.checkout.infra.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckoutEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishCheckoutCreatedEvent(Object checkoutDTO) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.CHECKOUT_EXCHANGE,
                RabbitMQConfig.ROUTING_KEY_CHECKOUT_CREATED,
                checkoutDTO
        );
        log.info("Checkout created event sent to checkout queue {}", checkoutDTO);
    }
}