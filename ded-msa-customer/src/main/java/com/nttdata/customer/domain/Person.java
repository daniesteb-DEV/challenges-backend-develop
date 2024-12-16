package com.nttdata.customer.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Generated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {

  Long personId;
  String identification;
  String name;
  String gender;
  int age;
  String address;
  String phone;
}
