package br.com.nordestech.checkout.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Customer {

    private String name;
    private String email;
    private String phoneNumber;

}
