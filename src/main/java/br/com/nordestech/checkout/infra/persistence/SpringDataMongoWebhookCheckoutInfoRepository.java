package br.com.nordestech.checkout.infra.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpringDataMongoWebhookCheckoutInfoRepository extends MongoRepository<WebhookCheckoutInfoDocument, String> {

    Boolean existsByOrderNsu(String orderNsu);

    Optional<WebhookCheckoutInfoDocument> findWebhookCheckoutInfoDocumentByOrderNsu(String orderNsu);

}
