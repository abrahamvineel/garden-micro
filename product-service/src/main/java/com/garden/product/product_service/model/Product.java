package com.garden.product.product_service.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    private String id;
    private String name;
    private String type;
    private double price;
}
