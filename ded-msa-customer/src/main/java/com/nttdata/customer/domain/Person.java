package com.nttdata.customer.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
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
  @Pattern(regexp = "^(?:\\d{8,10}|[A-Za-z]\\d{7,8}|\\d{13})$")
  String identification;
  String name;
  String gender;
  int age;
  String address;
  String phone;
}
