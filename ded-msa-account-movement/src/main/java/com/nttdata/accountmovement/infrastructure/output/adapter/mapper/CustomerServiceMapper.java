package com.nttdata.accountmovement.infrastructure.output.adapter.mapper;

import com.nttdata.accountmovement.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerServiceMapper {

  @Mapping(target = "customerId", source = "id")
  @Mapping(target = "customerName", source = "name")
  Customer toCustomer(com.nttdata.accountmovement.infrastructure.output.customer.client.models.Customer customer);
}
