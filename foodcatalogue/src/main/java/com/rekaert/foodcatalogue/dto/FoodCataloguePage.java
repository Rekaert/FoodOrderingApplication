package com.rekaert.foodcatalogue.dto;

import com.rekaert.foodcatalogue.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCataloguePage {

	List<FoodItem> foodItemsList;
	Restaurant restaurant;
}
