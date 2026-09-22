package br.com.nordestech.checkout.infra.persistence;

import br.com.nordestech.checkout.domain.model.CheckoutInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpringDataMongoCheckoutInfoRepository extends MongoRepository<CheckoutInfoDocument, String> {
    Optional<CheckoutInfoDocument> getById(String id);

    Optional<CheckoutInfoDocument> findCheckoutInfoDocumentByTransactionNsu(String transactionNsu);
}
