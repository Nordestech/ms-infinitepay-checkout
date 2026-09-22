package br.com.nordestech.checkout.infra.persistence;

import br.com.nordestech.checkout.domain.exception.CheckoutNotFoundException;
import br.com.nordestech.checkout.domain.model.CheckoutInfo;
import br.com.nordestech.checkout.domain.repository.CheckoutInfoRepository;
import br.com.nordestech.checkout.infra.persistence.mapper.CheckoutInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckoutInfoRepositoryImpl implements CheckoutInfoRepository {

    private final CheckoutInfoMapper mapper;
    private final SpringDataMongoCheckoutInfoRepository repository;

    @Override
    public CheckoutInfo save(CheckoutInfo checkoutInfo) {
        return mapper.toCheckoutInfo(repository.save(mapper.toCheckoutInfoDocument(checkoutInfo)));
    }

    @Override
    public CheckoutInfo getCheckoutInfo(String checkoutInfoId) {
        Optional<CheckoutInfoDocument> checkoutInfoDocument = repository.findById(checkoutInfoId);

        if (checkoutInfoDocument.isEmpty()) {
            throw new CheckoutNotFoundException("Checkout not found for id: " + checkoutInfoId);
        }

        return mapper.toCheckoutInfo(checkoutInfoDocument.get());
    }

    @Override
    public CheckoutInfo getCheckoutInfoByTransactionNsu(String transactionNsu) {
        Optional<CheckoutInfoDocument> checkoutInfoDocument = repository.findCheckoutInfoDocumentByTransactionNsu(transactionNsu);

        return checkoutInfoDocument.map(mapper::toCheckoutInfo).orElse(null);

    }
}
