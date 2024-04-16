package com.rekaert.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTOFromFE {

	private List<FoodItemsDTO> foodItemsList;
	private Integer uid;
	private RestaurantDTO restaurant;
}
