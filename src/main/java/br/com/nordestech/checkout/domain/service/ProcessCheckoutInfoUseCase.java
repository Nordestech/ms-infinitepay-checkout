package br.com.nordestech.checkout.domain.service;

import br.com.nordestech.checkout.domain.model.CheckoutInfo;
import br.com.nordestech.checkout.domain.repository.CheckoutInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessCheckoutInfoUseCase {

    private final CheckoutInfoRepository checkoutInfoRepository;

    public CheckoutInfo process(CheckoutInfo checkoutInfo){

        return checkoutInfoRepository.save(checkoutInfo);

    }

}
