package br.com.nordestech.checkout.domain.service;

import br.com.nordestech.checkout.domain.model.WebhookCheckoutInfo;
import br.com.nordestech.checkout.domain.repository.WebhookCheckoutInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetWebhookCheckoutInfoUseCase {

    private final WebhookCheckoutInfoRepository repository;

    public WebhookCheckoutInfo findByOrderNsu(String orderNsu){
        return repository.findByOrderNsu(orderNsu);
    }

}
