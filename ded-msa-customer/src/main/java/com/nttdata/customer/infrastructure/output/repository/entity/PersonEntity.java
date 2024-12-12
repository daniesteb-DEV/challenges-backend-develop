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
    public Long id;
    @Column(value = "identification")
    public String identification;
    @Column(value = "name")
    public String name;
    @Column(value = "gender")
    public String gender;
    @Column(value = "age")
    public Integer age;
    @Column(value = "address")
    public String address;
    @Column(value = "phone")
    public String phone;
}
