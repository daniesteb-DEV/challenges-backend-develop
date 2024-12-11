package com.nttdata.customer.infrastructure.output.repository.mapper;

import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.infrastructure.output.repository.entity.CustomerEntity;
import com.nttdata.customer.infrastructure.output.repository.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {

  @Mapping(target = "customerId", source = "id")
  @Mapping(target = "personId", source = "personId.id")
  @Mapping(target = "name", source = "personId.name")
  @Mapping(target = "gender", source = "personId.gender")
  @Mapping(target = "age", source = "personId.age")
  @Mapping(target = "address", source = "personId.address")
  @Mapping(target = "phone", source = "personId.phone")
  Customer toCustomer(CustomerEntity customerEntity);

  @Mapping(target = "id", source = "customerId")
  @Mapping(target = "personId.id", source = "personId")
  @Mapping(target = "personId.name", source = "name")
  @Mapping(target = "personId.gender", source = "gender")
  @Mapping(target = "personId.age", source = "age")
  @Mapping(target = "personId.address", source = "address")
  @Mapping(target = "personId.phone", source = "phone")
  CustomerEntity toCustomerEntity(Customer customer);

  @Mapping(target = "id", source = "personId")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "gender", source = "gender")
  @Mapping(target = "age", source = "age")
  @Mapping(target = "address", source = "address")
  @Mapping(target = "phone", source = "phone")
  PersonEntity toPersonEntity(Customer customer);

  @Mapping(target = "name", source = "name")
  @Mapping(target = "gender", source = "gender")
  @Mapping(target = "age", source = "age")
  @Mapping(target = "address", source = "address")
  @Mapping(target = "phone", source = "phone")
  @Mapping(target = "password", source = "password")
  @Mapping(target = "status", source = "status")
  void updateCustomer(@MappingTarget Customer customer, Customer customerUpdated);

}
