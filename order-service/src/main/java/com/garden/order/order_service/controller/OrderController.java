package com.garden.order.order_service.controller;

import com.garden.order.order_service.dto.OrderRequest;
import com.garden.order.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    public void placeOrder(@RequestBody OrderRequest request) {
        orderService.createOrder(request);
    }
}
