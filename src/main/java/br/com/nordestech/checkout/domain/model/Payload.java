package br.com.nordestech.checkout.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Payload {

    private String payloadId;
    private String handle;
    private List<Item> items;
    private String orderNsu;
    private String redirectUrl;
    private Customer customer;
    private Address address;

    public Payload(String handle, List<Item> items, String orderNsu, String redirectUrl, Customer customer) {
        this.handle = handle;
        this.items = items;
        this.orderNsu = orderNsu;
        this.redirectUrl = redirectUrl;
        this.customer = customer;
    }

    protected  Payload() {
    }
}
