package com.garden.product.product_service.service;

import com.garden.product.product_service.dto.ProductRequest;
import com.garden.product.product_service.dto.ProductResponse;
import com.garden.product.product_service.model.Product;
import com.garden.product.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void addProduct(ProductRequest request) {

        Product product = Product.builder()
                                 .name(request.getName())
                                 .type(request.getType())
                                 .price(request.getPrice())
                                 .build();

        productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                                .stream()
                                .map(this::mapToProductResponse)
                                .collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                              .id(product.getId())
                              .name(product.getName())
                              .type(product.getType())
                              .price(product.getPrice())
                              .build();
    }
}
