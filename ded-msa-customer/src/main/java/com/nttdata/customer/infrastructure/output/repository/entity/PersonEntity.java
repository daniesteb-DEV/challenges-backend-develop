package com.nttdata.customer.infrastructure.output.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("person")
@Builder
@Getter
@Setter
public class PersonEntity {
  @Id
  String id;
  @Column("name")
  String name;
  @Column("gender")
  String gender;
  @Column("age")
  int age;
  @Column("address")
  String address;
  @Column("phone")
  String phone;
}
