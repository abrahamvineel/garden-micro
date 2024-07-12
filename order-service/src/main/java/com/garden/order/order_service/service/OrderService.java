package com.garden.order.order_service.service;

import com.garden.order.order_service.dto.InventoryResponse;
import com.garden.order.order_service.dto.ItemDto;
import com.garden.order.order_service.dto.OrderRequest;
import com.garden.order.order_service.model.Item;
import com.garden.order.order_service.model.Order;
import com.garden.order.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setNumber(UUID.randomUUID().toString());
        List<Item> items = orderRequest.getOrderItems()
                .stream()
                .map(this::mapToItems)
                .toList();
        order.setOrderItems(items);

        List<String> skuCodes = order.getOrderItems()
                                     .stream()
                                     .map(Item::getSkuCode)
                                     .toList();

        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:9191/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);


        if(allProductsInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock");
        }
    }

    private Item mapToItems(ItemDto item) {
        return Item.builder()
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .name(item.getName())
                .type(item.getType())
                .skuCode(item.getSkuCode())
                .build();
    }
}
