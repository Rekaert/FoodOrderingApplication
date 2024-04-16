package com.rekaert.foodcatalogue.service;

import com.rekaert.foodcatalogue.dto.FoodCataloguePage;
import com.rekaert.foodcatalogue.dto.FoodItemDTO;
import com.rekaert.foodcatalogue.dto.Restaurant;
import com.rekaert.foodcatalogue.entity.FoodItem;
import com.rekaert.foodcatalogue.mapper.FoodItemMapper;
import com.rekaert.foodcatalogue.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

	@Autowired
	FoodItemRepository foodItemRepository;

	@Autowired
	RestTemplate restTemplate;

	public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
		FoodItem foodItemSavedInDB = foodItemRepository.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
		return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItemSavedInDB);
	}

	public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
		List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
		Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
		return createFoodCataloguePage(foodItemList, restaurant);
	}

	private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
		FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
		foodCataloguePage.setFoodItemsList(foodItemList);
		foodCataloguePage.setRestaurant(restaurant);
		return  foodCataloguePage;
	}

	private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
		return restTemplate.getForObject("http://RESTAURANT-SERVICE/fetchById/" + restaurantId, Restaurant.class);
	}

	private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
		return foodItemRepository.findByRestauranteId(restaurantId);
	}
}
