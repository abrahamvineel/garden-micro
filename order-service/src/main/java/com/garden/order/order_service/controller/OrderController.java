package com.garden.order.order_service.controller;

import com.garden.order.order_service.dto.OrderRequest;
import com.garden.order.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest request) {
        return CompletableFuture.supplyAsync(() -> orderService.createOrder(request));
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest request, Throwable exception) {
        return CompletableFuture.supplyAsync(() -> "Sorry, could not place the order. Please try ordering after sometime");
    }
}
