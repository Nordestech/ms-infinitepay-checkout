package br.com.nordestech.checkout.port.output.infinitePay.service;

import br.com.nordestech.checkout.domain.model.Payload;
import br.com.nordestech.checkout.port.output.infinitePay.api.CheckoutAPI;
import br.com.nordestech.checkout.port.output.infinitePay.exception.CheckoutException;
import br.com.nordestech.checkout.port.output.infinitePay.mapper.PayloadMapper;
import br.com.nordestech.checkout.port.output.infinitePay.model.Checkout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckoutService {

    private final CheckoutAPI checkoutAPI;
    private final PayloadMapper mapper;

    public Checkout executeCheckout(Payload payload) {

        try {

            return checkoutAPI.createCheckoutURL(mapper.toPayloadDTO(payload));

        } catch (feign.FeignException e) {

            throw new CheckoutException("Error at checkout: " + e.getMessage());

        }
    }

}
