package com.rekaert.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	private Integer orderid;
	private List<FoodItemsDTO> foodItemsList;
	private UserDTO uid;
	private RestaurantDTO restaurant;
}
