package com.garden.order.order_service.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class ItemDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;
    private final Integer quantity;
    private final String name;
    private final String type;
    private final String skuCode;
    private final Double price;
}
