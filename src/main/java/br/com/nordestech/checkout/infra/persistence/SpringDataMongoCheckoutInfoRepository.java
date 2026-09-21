package br.com.nordestech.checkout.infra.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataMongoCheckoutInfoRepository extends MongoRepository<CheckoutInfoDocument, String> {
}
