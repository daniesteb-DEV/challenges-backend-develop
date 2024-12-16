package com.nttdata.customer.util;

import static com.nttdata.customer.util.Constants.CUSTOMER_ID;
import static com.nttdata.customer.util.Constants.CUSTOMER_PASSWORD;
import static com.nttdata.customer.util.Constants.CUSTOMER_STATUS;
import static com.nttdata.customer.util.Constants.PERSON_ADDRESS;
import static com.nttdata.customer.util.Constants.PERSON_AGE;
import static com.nttdata.customer.util.Constants.PERSON_GENDER;
import static com.nttdata.customer.util.Constants.PERSON_ID;
import static com.nttdata.customer.util.Constants.PERSON_IDENTIFICATION;
import static com.nttdata.customer.util.Constants.PERSON_NAME;
import static com.nttdata.customer.util.Constants.PERSON_PHONE;
import static com.nttdata.customer.util.DomainBuilderUtil.buildCustomer;
import static com.nttdata.customer.util.DomainBuilderUtil.buildPerson;

import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.domain.Person;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DomainMockDataUtil {

  public static Customer getCustomer() {
    return buildCustomer(
        CUSTOMER_ID,
        CUSTOMER_PASSWORD,
        CUSTOMER_STATUS,
        PERSON_ID,
        PERSON_IDENTIFICATION,
        PERSON_NAME,
        PERSON_GENDER,
        PERSON_AGE,
        PERSON_ADDRESS,
        PERSON_PHONE
    );
  }

  public static Person getPerson() {
    return buildPerson(
        PERSON_ID,
        PERSON_IDENTIFICATION,
        PERSON_NAME,
        PERSON_GENDER,
        PERSON_AGE,
        PERSON_ADDRESS,
        PERSON_PHONE
    );
  }
}
