package com.nttdata.customer.infrastructure.output.repository.mapper;

import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.infrastructure.output.repository.entity.CustomerEntity;
import com.nttdata.customer.infrastructure.output.repository.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {

  @Mapping(target = "customerId", source = "customerEntity.id")
  @Mapping(target = "personId", source = "person.id")
  @Mapping(target = "identification", source = "person.identification")
  @Mapping(target = "name", source = "person.name")
  @Mapping(target = "gender", source = "person.gender")
  @Mapping(target = "age", source = "person.age")
  @Mapping(target = "address", source = "person.address")
  @Mapping(target = "phone", source = "person.phone")
  Customer toCustomer(CustomerEntity customerEntity, PersonEntity person);

  @Mapping(target = "id", source = "customerId")
  CustomerEntity toCustomerEntity(Customer customer);

  @Mapping(target = "id", source = "personId")
  @Mapping(target = "identification", source = "identification")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "gender", source = "gender")
  @Mapping(target = "age", source = "age")
  @Mapping(target = "address", source = "address")
  @Mapping(target = "phone", source = "phone")
  PersonEntity toPersonEntity(Customer customer);

}
