package com.rekaert.order.service;

import com.rekaert.order.dto.OrderDTO;
import com.rekaert.order.dto.OrderDTOFromFE;
import com.rekaert.order.dto.UserDTO;
import com.rekaert.order.entity.Order;
import com.rekaert.order.mapper.OrderMapper;
import com.rekaert.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	SequenceGenerator sequenceGenerator;

	@Autowired
	RestTemplate restTemplate;

	public OrderDTO saveOrderInDB(OrderDTOFromFE orderDetails) {
		Integer newOrderId = sequenceGenerator.generateNextOrderId();
		UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUid());
		Order orderToBeSaved = new Order(newOrderId, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO);
		orderRepository.save(orderToBeSaved);

		return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);
	}

	private UserDTO fetchUserDetailsFromUserId(Integer uid) {

		return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/" + uid, UserDTO.class);
	}
}
