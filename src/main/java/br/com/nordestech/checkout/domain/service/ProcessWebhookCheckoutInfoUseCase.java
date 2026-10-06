package br.com.nordestech.checkout.domain.service;

import br.com.nordestech.checkout.domain.model.WebhookCheckoutInfo;
import br.com.nordestech.checkout.domain.repository.WebhookCheckoutInfoRepository;
import br.com.nordestech.checkout.port.output.producer.PaymentApprovedEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessWebhookCheckoutInfoUseCase {

    private final WebhookCheckoutInfoRepository repository;

    private final PaymentApprovedEventPublisher eventPublisher;

    public void save(WebhookCheckoutInfo webhookCheckoutInfo) {
        if (!repository.existsByOrderNsu(webhookCheckoutInfo.getOrderNsu())) {

            log.info("Saving webhook checkout info: 'order_nsu' = '{}'", webhookCheckoutInfo.getOrderNsu());
            repository.save(webhookCheckoutInfo);

            log.info("Publish payment approved request received: 'order_nsu' = '{}'", webhookCheckoutInfo.getOrderNsu());
            eventPublisher.publishPaymentApprovedEvent(webhookCheckoutInfo);

        }
    }

}
