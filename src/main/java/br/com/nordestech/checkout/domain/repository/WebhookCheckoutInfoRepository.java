package br.com.nordestech.checkout.domain.repository;

import br.com.nordestech.checkout.domain.model.WebhookCheckoutInfo;

public interface WebhookCheckoutInfoRepository {

    void save(WebhookCheckoutInfo webhookCheckoutInfo);

    WebhookCheckoutInfo findByOrderNsu(String orderNsu);

    Boolean existsByOrderNsu(String orderNsu);

}
