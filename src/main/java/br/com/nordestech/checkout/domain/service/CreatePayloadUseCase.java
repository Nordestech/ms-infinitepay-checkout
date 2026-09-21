package br.com.nordestech.checkout.domain.service;

import br.com.nordestech.checkout.domain.model.Payload;
import br.com.nordestech.checkout.domain.model.dto.CheckoutDTO;
import br.com.nordestech.checkout.port.output.exception.CheckoutException;
import br.com.nordestech.checkout.port.output.model.Checkout;
import br.com.nordestech.checkout.port.output.service.CheckoutService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePayloadUseCase {

    private final CheckoutService checkoutService;

    public CheckoutDTO createPayload(Payload payload) {
        try {
            Checkout checkout = checkoutService.executeCheckout(payload);
            return CheckoutDTO.builder()
                    .checkoutUrl(checkout.checkoutUrl())
                    .build();
        } catch (FeignException e) {
            throw new CheckoutException("Error creating checkout URL");
        }
    }

}
