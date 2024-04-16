package com.rekaert.foodcatalogue.mapper;

import com.rekaert.foodcatalogue.dto.FoodItemDTO;
import com.rekaert.foodcatalogue.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {

	FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

	FoodItem mapFoodItemDTOToFoodItem(FoodItemDTO FoodItemDTO);
	FoodItemDTO mapFoodItemToFoodItemDTO(FoodItem foodItem);
}
