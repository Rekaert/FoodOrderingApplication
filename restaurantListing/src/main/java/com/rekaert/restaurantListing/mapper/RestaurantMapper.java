package com.rekaert.restaurantListing.mapper;

import com.rekaert.restaurantListing.dto.RestaurantDTO;
import com.rekaert.restaurantListing.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {
	RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

	Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);
	RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);
}
