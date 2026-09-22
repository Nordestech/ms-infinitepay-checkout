package br.com.nordestech.checkout.port.output.model.dto;

import br.com.nordestech.checkout.domain.model.Address;
import br.com.nordestech.checkout.domain.model.Customer;
import br.com.nordestech.checkout.domain.model.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record PayloadDTO(

        String handle,

        List<Item> items,

        @JsonProperty("order_nsu")
        String orderNsu,

        @JsonProperty("redirect_url")
        String redirectUrl,

        Customer customer,

        Address address

) {

}