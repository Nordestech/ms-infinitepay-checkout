package br.com.nordestech.checkout.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Customer {

    private String name;
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

}
