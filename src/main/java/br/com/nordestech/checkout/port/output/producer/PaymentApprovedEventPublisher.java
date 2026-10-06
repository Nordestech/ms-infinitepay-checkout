package br.com.nordestech.checkout.port.output.producer;

import br.com.nordestech.checkout.domain.model.WebhookCheckoutInfo;
import br.com.nordestech.checkout.infra.config.RabbitMQConfig;
import br.com.nordestech.checkout.port.output.producer.dto.PaymentApprovedDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentApprovedEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishPaymentApprovedEvent(WebhookCheckoutInfo webhookCheckoutInfo) {

        PaymentApprovedDTO paymentApprovedDTO = toPaymentApprovedDTO(webhookCheckoutInfo);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.PAYMENT_APPROVED_EXCHANGE,
                RabbitMQConfig.ROUTING_KEY_PAYMENT_APPROVED,
                paymentApprovedDTO
        );
        log.info("Payment approved event sent to queue: 'order_nsu' = '{}'", paymentApprovedDTO.orderNsu());
    }

    private PaymentApprovedDTO toPaymentApprovedDTO(WebhookCheckoutInfo webhookCheckoutInfo) {

        return PaymentApprovedDTO.builder()
                .checkoutId(webhookCheckoutInfo.getCheckoutId())
                .orderNsu(webhookCheckoutInfo.getOrderNsu())
                .transactionNsu(webhookCheckoutInfo.getTransactionNsu())
                .receiptUrl(webhookCheckoutInfo.getReceiptUrl())
                .amount(webhookCheckoutInfo.getAmount())
                .paidAmount(webhookCheckoutInfo.getPaidAmount())
                .build();
    }
}