package com.garden.order.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OrderRequest {
    private final List<ItemDto> orderItems;
}
