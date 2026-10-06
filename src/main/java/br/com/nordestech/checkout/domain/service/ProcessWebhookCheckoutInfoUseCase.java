package br.com.nordestech.checkout.domain.service;

import br.com.nordestech.checkout.domain.model.WebhookCheckoutInfo;
import br.com.nordestech.checkout.domain.repository.WebhookCheckoutInfoRepository;
import br.com.nordestech.checkout.port.output.producer.CheckoutEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessWebhookCheckoutInfoUseCase {

    private final WebhookCheckoutInfoRepository repository;

    private final CheckoutEventPublisher eventPublisher;

    @Transactional
    public void save(WebhookCheckoutInfo webhookCheckoutInfo) {
        if (!repository.existsByOrderNsu(webhookCheckoutInfo.getOrderNsu())) {
            repository.save(webhookCheckoutInfo);

            eventPublisher.publishCheckoutCreatedEvent(webhookCheckoutInfo);
        }
    }

}
