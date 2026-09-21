package br.com.nordestech.checkout.port.output.model.dto;

import br.com.nordestech.checkout.domain.model.Address;
import br.com.nordestech.checkout.domain.model.Customer;
import br.com.nordestech.checkout.domain.model.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class PayloadDTO {

        private String handle;

        private List<Item> items;

        @JsonProperty("order_nsu")
        private String orderNsu;

        @JsonProperty("redirect_url")
        private String redirectUrl;

        private Customer customer;

        private Address address;

}