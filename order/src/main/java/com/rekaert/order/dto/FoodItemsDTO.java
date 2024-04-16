package com.rekaert.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemsDTO {

	private int id;
	private String item;
	private String itemDescription;
	private boolean isVegetarian;
	private Number price;
	private Integer restaurantId;
	private Integer quantity;
}
