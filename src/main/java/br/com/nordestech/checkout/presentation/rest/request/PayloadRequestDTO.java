package br.com.nordestech.checkout.presentation.rest.request;

import br.com.nordestech.checkout.domain.model.Address;
import br.com.nordestech.checkout.domain.model.Customer;
import br.com.nordestech.checkout.domain.model.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record PayloadRequestDTO(

        @NotNull(message = "items é obrigatório")
        @JsonProperty("items")
        @Valid
        List<ItemDTO> items,

        @NotBlank(message = "order_nsu é obrigatório")
        @JsonProperty("order_nsu")
        String orderNsu,

        @NotBlank(message = "redirect_url é obrigatório")
        @JsonProperty("redirect_url")
        String redirectUrl,

        @NotNull(message = "customer é obrigatório")
        @JsonProperty("customer")
        @Valid
        CustomerDTO customer,

        @NotNull(message = "address é obrigatório")
        @JsonProperty("address")
        @Valid
        AddressDTO address

) {

    public record ItemDTO(
            @NotNull(message = "quantity é obrigatório")
            @Min(value = 1, message = "quantity mínima é 1")
            @JsonProperty("quantity")
            Integer quantity,

            @NotNull(message = "price é obrigatório")
            @DecimalMin(value = "0.01", message = "price mínimo é 0.01")
            @JsonProperty("price")
            BigDecimal price,

            @NotBlank(message = "description é obrigatório")
            @Size(min = 10, max = 50, message = "description deve ter entre 10 a 50 caracteres")
            @JsonProperty("description")
            String description
    ) {
        public Item toModel() {
            return new Item(this.quantity, this.price, this.description);
        }
    }

    public record CustomerDTO(
            @NotBlank(message = "nome é obrigatório")
            @JsonProperty("name")
            String name,

            @NotBlank(message = "email é obrigatório")
            @JsonProperty("email")
            String email,

            @NotBlank(message = "phoneNumber é obrigatório")
            @JsonProperty("phone_number")
            String phoneNumber
    ) {
        public Customer toModel() {
            return new Customer(this.name, this.email, this.phoneNumber);
        }
    }

    public record AddressDTO(
            @NotBlank(message = "cep é obrigatório")
            @JsonProperty("cep")
            String cep,

            @NotBlank(message = "street é obrigatório")
            @JsonProperty("street")
            String street,

            @NotBlank(message = "neighborhood é obrigatório")
            @JsonProperty("neighborhood")
            String neighborhood,

            @NotBlank(message = "number é obrigatório")
            @JsonProperty("number")
            String number,

            @NotBlank(message = "complement é obrigatório")
            @JsonProperty("complement")
            String complement
    ) {
        public Address toModel() {
            return new Address(this.cep, this.street, this.neighborhood, this.number, this.complement);
        }
    }

}