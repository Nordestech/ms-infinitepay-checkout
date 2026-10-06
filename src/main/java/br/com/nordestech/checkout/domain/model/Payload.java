package br.com.nordestech.checkout.domain.model;

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
    private String orderNsu;
    private String redirectUrl;
    private String webhookUrl;
    private Customer customer;
    private Address address;

}
