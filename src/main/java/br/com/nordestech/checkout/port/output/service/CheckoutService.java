package br.com.nordestech.checkout.port.output.service;

import br.com.nordestech.checkout.domain.model.Payload;
import br.com.nordestech.checkout.port.output.api.CheckoutAPI;
import br.com.nordestech.checkout.port.output.mapper.PayloadToCheckoutDTOMapper;
import br.com.nordestech.checkout.port.output.model.Checkout;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckoutService {

    private final CheckoutAPI checkoutAPI;
    private final PayloadToCheckoutDTOMapper mapper;

    public Checkout executeCheckout(Payload payload) {

        log.info("tentando criar checkout");

        try {
            Checkout checkout = checkoutAPI.createCheckoutURL(mapper.toPayloadDTO(payload));
            System.out.println("Checkout criado: " + checkout);
            return checkout;
        } catch (feign.FeignException e) {
            System.err.println("Status da resposta: " + e.status());
            System.err.println("Corpo do erro retornado pela API: " + e.contentUTF8());
        }
        return null;
    }

}
