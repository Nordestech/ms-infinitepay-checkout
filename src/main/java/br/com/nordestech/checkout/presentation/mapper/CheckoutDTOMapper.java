package br.com.nordestech.checkout.presentation.mapper;

import br.com.nordestech.checkout.domain.model.dto.CheckoutDTO;
import br.com.nordestech.checkout.presentation.rest.response.CheckoutResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CheckoutDTOMapper {

    public CheckoutResponseDTO toCheckoutResponseDTO(CheckoutDTO checkout) {

        return CheckoutResponseDTO.builder()
                .checkoutUrl(checkout.checkoutUrl())
                .build();
    }

}
