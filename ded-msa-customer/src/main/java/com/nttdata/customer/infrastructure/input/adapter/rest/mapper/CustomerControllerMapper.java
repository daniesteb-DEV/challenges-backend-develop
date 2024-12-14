package com.nttdata.customer.infrastructure.input.adapter.rest.mapper;

import com.nttdata.customer.infrastructure.input.adapter.rest.models.Customer;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.CustomerUpdate;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.PostCustomerResponse;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.PutCustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerControllerMapper {

  @Mapping(target = "id", source = "identification")
  Customer toCustomer(com.nttdata.customer.domain.Customer customer);

  @Mapping(target = "identification", source = "id")
  com.nttdata.customer.domain.Customer toCustomer(Customer customer);

  @Mapping(target = "customerId", ignore = true)
  @Mapping(target = "personId", ignore = true)
  com.nttdata.customer.domain.Customer toCustomer(CustomerUpdate customerUpdate);

  @Mapping(target = "id", source = "identification")
  PostCustomerResponse toPostCustomerResponse(com.nttdata.customer.domain.Customer customer);

  @Mapping(target = "id", source = "identification")
  PutCustomerResponse toPutCustomerResponse(com.nttdata.customer.domain.Customer customer);

  @Mapping(target = "name", source = "name")
  @Mapping(target = "gender", source = "gender")
  @Mapping(target = "age", source = "age")
  @Mapping(target = "address", source = "address")
  @Mapping(target = "phone", source = "phone")
  @Mapping(target = "password", source = "password")
  @Mapping(target = "status", source = "status")
  void updateCustomer(@MappingTarget com.nttdata.customer.domain.Customer customer, com.nttdata.customer.domain.Customer customerUpdated);
}
