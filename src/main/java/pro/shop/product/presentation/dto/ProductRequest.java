package pro.shop.product.presentation.dto;

import pro.shop.product.application.dto.ProductCommand;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String status,
        String operatorId
) {

    public ProductCommand toCommand() {
        UUID operator = operatorId != null ? UUID.fromString(operatorId) : null;
        return new ProductCommand(name, description, price, stock, status, operator);
    }
}
