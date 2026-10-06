package br.com.nordestech.checkout.presentation.rest.request;

import br.com.nordestech.checkout.domain.model.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record WebhookCheckoutInfoRequestDTO(

        @NotBlank
        @JsonProperty("invoice_slug")
        String invoiceSlug,

        @NotNull
        BigDecimal amount,

        @NotNull
        @JsonProperty("paid_amount")
        BigDecimal paidAmount,

        @NotNull
        Integer installments,

        @NotBlank
        @JsonProperty("capture_method")
        String captureMethod,

        @NotBlank
        @JsonProperty("transaction_nsu")
        String transactionNsu,

        @NotBlank
        @JsonProperty("order_nsu")
        String orderNsu,

        @NotBlank
        @JsonProperty("receipt_url")
        String receiptUrl,

        @NotNull
        List<ItemDTO> items

) {

    public record ItemDTO(

            @NotNull
            Integer quantity,

            @NotNull
            BigDecimal price,

            @NotBlank
            String description
    ) {
        public Item toModel() {
            return new Item(this.quantity, this.price, this.description);
        }
    }
}
