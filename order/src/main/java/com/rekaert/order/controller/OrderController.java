package com.rekaert.order.controller;

import com.rekaert.order.dto.OrderDTO;
import com.rekaert.order.dto.OrderDTOFromFE;
import com.rekaert.order.repository.OrderRepository;
import com.rekaert.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("saveOrder")
	public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTOFromFE orderDetails) {
		OrderDTO orderSavedInDB = orderService.saveOrderInDB(orderDetails);
		return  new ResponseEntity<>(orderSavedInDB, HttpStatus.CREATED);
	}
}