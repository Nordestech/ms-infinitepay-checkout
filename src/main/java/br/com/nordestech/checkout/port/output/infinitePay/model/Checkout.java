package br.com.nordestech.checkout.port.output.infinitePay.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Checkout(

        @JsonProperty("url")
        String checkoutUrl

) {
}
