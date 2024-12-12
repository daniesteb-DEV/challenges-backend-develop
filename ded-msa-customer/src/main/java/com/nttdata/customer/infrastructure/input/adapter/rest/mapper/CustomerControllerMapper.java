package com.nttdata.customer.infrastructure.input.adapter.rest.mapper;

import com.nttdata.customer.infrastructure.input.adapter.rest.models.Customer;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.CustomerUpdate;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.PostCustomerResponse;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.PutCustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerControllerMapper {

  @Mapping(target = "id", source = "personId")
  Customer toCustomer(com.nttdata.customer.domain.Customer customer);

  @Mapping(target = "personId", source = "id")
  com.nttdata.customer.domain.Customer toCustomer(Customer customer);

  @Mapping(target = "customerId", ignore = true)
  @Mapping(target = "personId", ignore = true)
  com.nttdata.customer.domain.Customer toCustomer(CustomerUpdate customerUpdate);

  @Mapping(target = "id", source = "personId")
  PostCustomerResponse toPostCustomerResponse(com.nttdata.customer.domain.Customer customer);

  @Mapping(target = "id", source = "personId")
  PutCustomerResponse toPutCustomerResponse(com.nttdata.customer.domain.Customer customer);
}
