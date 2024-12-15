package com.nttdata.customer.util;

import com.nttdata.customer.infrastructure.input.adapter.rest.models.Customer;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.CustomerUpdate;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.PostCustomerResponse;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.PutCustomerResponse;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InfrastructureBuilderUtil {

  @NonNull
  public static Customer buildCustomer(
      String id,
      String name,
      String gender,
      Integer age,
      String address,
      String phone,
      String password,
      String status
  ) {
    return new Customer().id(id)
        .name(name)
        .gender(gender)
        .age(age)
        .address(address)
        .phone(phone)
        .password(password)
        .status(status);
  }

  @NonNull
  public static CustomerUpdate buildCustomerUpdate(
      String name,
      String gender,
      Integer age,
      String address,
      String phone,
      String password,
      String status
  ) {
    return new CustomerUpdate().name(name)
        .gender(gender)
        .age(age)
        .address(address)
        .phone(phone)
        .password(password)
        .status(status);
  }

  @NonNull
  public static PostCustomerResponse buildPostCustomerResponse(String id) {
    return new PostCustomerResponse().id(id);
  }

  @NonNull
  public static PutCustomerResponse buildPutCustomerResponse(String id) {
    return new PutCustomerResponse().id(id);
  }

}
