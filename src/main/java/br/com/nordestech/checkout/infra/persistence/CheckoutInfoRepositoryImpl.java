package br.com.nordestech.checkout.infra.persistence;

import br.com.nordestech.checkout.domain.model.CheckoutInfo;
import br.com.nordestech.checkout.domain.repository.CheckoutInfoRepository;
import br.com.nordestech.checkout.infra.persistence.mapper.CheckoutInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckoutInfoRepositoryImpl implements CheckoutInfoRepository {

    private final CheckoutInfoMapper mapper;
    private final SpringDataMongoCheckoutInfoRepository repository;

    @Override
    public CheckoutInfo save(CheckoutInfo checkoutInfo) {
        return mapper.toCheckoutInfo(repository.save(mapper.toCheckoutInfoDocument(checkoutInfo)));
    }
}
