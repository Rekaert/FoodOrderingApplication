package com.rekaert.foodcatalogue.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {

	private int id;
	private String item;
	private String itemDescription;
	private boolean isVegetarian;
	private Number price;
	private Integer restaurantId;
	private Integer quantity;
}
