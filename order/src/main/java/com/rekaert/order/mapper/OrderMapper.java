package com.rekaert.order.mapper;

import com.rekaert.order.dto.OrderDTO;
import com.rekaert.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

	Order mapOrderDTOToOrder(OrderDTO userDTO);
	OrderDTO mapOrderToOrderDTO(Order user);

}
