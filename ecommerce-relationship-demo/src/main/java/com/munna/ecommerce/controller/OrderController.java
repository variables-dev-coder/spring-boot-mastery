package com.munna.ecommerce.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munna.ecommerce.entity.Order;
import com.munna.ecommerce.service.OrderService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping("/{userId}")
	public Order createOrder(@PathVariable Long userId,
							 @RequestBody Order order) {
		
		return orderService.createOrder(userId, order);
	}

}
