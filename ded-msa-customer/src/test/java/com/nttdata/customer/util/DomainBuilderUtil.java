package com.nttdata.customer.util;

import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.domain.Person;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DomainBuilderUtil {

  @NonNull
  public static Customer buildCustomer(
      String customerId,
      String password,
      String status,
      Long personId,
      String identification,
      String name,
      String gender,
      int age,
      String address,
      String phone
  ) {
    return Customer.builder()
        .customerId(customerId)
        .password(password)
        .status(status)
        .personId(personId)
        .identification(identification)
        .name(name)
        .gender(gender)
        .age(age)
        .address(address)
        .phone(phone)
        .build();
  }

  @NonNull
  public static Person buildPerson(
      Long personId,
      String identification,
      String name,
      String gender,
      int age,
      String address,
      String phone
  ) {
    return Person.builder()
        .personId(personId)
        .identification(identification)
        .name(name)
        .gender(gender)
        .age(age)
        .address(address)
        .phone(phone)
        .build();
  }

}
