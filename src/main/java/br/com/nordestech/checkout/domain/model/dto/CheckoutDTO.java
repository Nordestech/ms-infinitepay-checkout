package br.com.nordestech.checkout.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CheckoutDTO(
        @JsonProperty("checkout_url")
        String checkoutUrl
) {
}
