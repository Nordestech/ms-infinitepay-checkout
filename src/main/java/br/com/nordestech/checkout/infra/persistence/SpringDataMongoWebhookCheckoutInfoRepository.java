package br.com.nordestech.checkout.infra.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpringDataMongoWebhookCheckoutInfoRepository extends MongoRepository<WebhookCheckoutInfoDocument, String> {

    Optional<WebhookCheckoutInfoDocument> findByOrderNsu(String orderNsu);

    Boolean existsByOrderNsu(String orderNsu);
}
