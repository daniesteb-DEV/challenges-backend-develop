package com.nttdata.customer.util;

import static com.nttdata.customer.util.Constants.CUSTOMER_PASSWORD;
import static com.nttdata.customer.util.Constants.CUSTOMER_STATUS;
import static com.nttdata.customer.util.Constants.PERSON_ADDRESS;
import static com.nttdata.customer.util.Constants.PERSON_AGE;
import static com.nttdata.customer.util.Constants.PERSON_GENDER;
import static com.nttdata.customer.util.Constants.PERSON_IDENTIFICATION;
import static com.nttdata.customer.util.Constants.PERSON_NAME;
import static com.nttdata.customer.util.Constants.PERSON_PHONE;

import com.nttdata.customer.infrastructure.input.adapter.rest.models.Customer;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.CustomerUpdate;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.PostCustomerResponse;
import com.nttdata.customer.infrastructure.input.adapter.rest.models.PutCustomerResponse;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InfrastructureMockDataUtil {

  @NonNull
  public static Customer getCustomer() {
    return new Customer().id(PERSON_IDENTIFICATION)
        .name(PERSON_NAME)
        .gender(PERSON_GENDER)
        .age(PERSON_AGE)
        .address(PERSON_ADDRESS)
        .phone(PERSON_PHONE)
        .password(CUSTOMER_PASSWORD)
        .status(CUSTOMER_STATUS);
  }

  @NonNull
  public static CustomerUpdate getCustomerUpdate() {
    return new CustomerUpdate().name(PERSON_NAME)
        .gender(PERSON_GENDER)
        .age(PERSON_AGE)
        .address(PERSON_ADDRESS)
        .phone(PERSON_PHONE)
        .password(CUSTOMER_PASSWORD)
        .status(CUSTOMER_STATUS);
  }

  @NonNull
  public static PostCustomerResponse getPostCustomerResponse() {
    return new PostCustomerResponse().id(PERSON_IDENTIFICATION);
  }

  @NonNull
  public static PutCustomerResponse getPutCustomerResponse() {
    return new PutCustomerResponse().id(PERSON_IDENTIFICATION);
  }

}
