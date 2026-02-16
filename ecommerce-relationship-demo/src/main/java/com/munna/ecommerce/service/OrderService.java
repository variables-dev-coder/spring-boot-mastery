package com.munna.ecommerce.service;

import org.springframework.stereotype.Service;

import com.munna.ecommerce.entity.Order;
import com.munna.ecommerce.entity.User;
import com.munna.ecommerce.repository.OrderRepository;
import com.munna.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	private final UserRepository userRepository;
	
	public Order createOrder(Long userId, Order order) {
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		order.setUser(user);
		
		return orderRepository.save(order);
	}

}
