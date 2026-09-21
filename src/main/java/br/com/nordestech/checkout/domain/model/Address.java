package br.com.nordestech.checkout.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Address {

    private String cep;
    private String street;
    private String neighborhood;
    private String number;
    private String complement;

    public Address toModel() {
        return new Address(this.cep, this.street, this.neighborhood, this.number, this.complement);
    }

}
