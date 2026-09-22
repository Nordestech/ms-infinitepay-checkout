package br.com.nordestech.checkout.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Payload {

    private String handle;
    private List<Item> items;

    @JsonProperty("order_nsu")
    private String orderNsu;

    @JsonProperty("redirect_url")
    private String redirectUrl;

    private Customer customer;
    private Address address;

}
