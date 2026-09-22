package br.com.nordestech.checkout.presentation.mapper;

import br.com.nordestech.checkout.port.output.model.Checkout;
import br.com.nordestech.checkout.presentation.rest.response.CheckoutResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CheckoutDTOMapper {

    public CheckoutResponseDTO toCheckoutResponseDTO(Checkout checkout) {

        return CheckoutResponseDTO.builder()
                .checkoutUrl(checkout.checkoutUrl())
                .build();
    }

}
