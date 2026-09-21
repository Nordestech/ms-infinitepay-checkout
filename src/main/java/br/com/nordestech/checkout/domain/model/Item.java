package br.com.nordestech.checkout.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Item {

    private Integer quantity;
    private BigDecimal price;
    private String description;

    public Item toModel() {
        return new Item(this.quantity, this.price, this.description);
    }

}
