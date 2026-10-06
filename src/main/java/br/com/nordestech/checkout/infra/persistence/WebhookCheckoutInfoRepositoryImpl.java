package br.com.nordestech.checkout.infra.persistence;

import br.com.nordestech.checkout.domain.exception.ResourceNotFoundException;
import br.com.nordestech.checkout.domain.model.WebhookCheckoutInfo;
import br.com.nordestech.checkout.domain.repository.WebhookCheckoutInfoRepository;
import br.com.nordestech.checkout.infra.persistence.mapper.WebhookCheckoutInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WebhookCheckoutInfoRepositoryImpl implements WebhookCheckoutInfoRepository {

    private final SpringDataMongoWebhookCheckoutInfoRepository repository;
    private final WebhookCheckoutInfoMapper mapper;

    @Override
    public void save(WebhookCheckoutInfo webhookCheckoutInfo) {
        repository.save(mapper.toWebhookCheckoutInfoDocument(webhookCheckoutInfo));
    }

    @Override
    public WebhookCheckoutInfo findByOrderNsu(String orderNsu) {
        Optional<WebhookCheckoutInfoDocument> checkoutInfoDocument = repository.findByOrderNsu(orderNsu);

        if (checkoutInfoDocument.isEmpty()) {
            throw new ResourceNotFoundException("Checkout Info", "orderNsu", orderNsu);
        }

        return mapper.toWebhookCheckoutInfo(checkoutInfoDocument.get());
    }

    @Override
    public Boolean existsByOrderNsu(String orderNsu) {
        return repository.existsByOrderNsu(orderNsu);
    }
}
