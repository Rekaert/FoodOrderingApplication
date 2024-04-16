package com.rekaert.order.entity;

import com.rekaert.order.dto.FoodItemsDTO;
import com.rekaert.order.dto.RestaurantDTO;
import com.rekaert.order.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {

	private Integer orderid;
	private List<FoodItemsDTO> foodItemsList;
	private RestaurantDTO restaurant;
	private UserDTO uid;

}
