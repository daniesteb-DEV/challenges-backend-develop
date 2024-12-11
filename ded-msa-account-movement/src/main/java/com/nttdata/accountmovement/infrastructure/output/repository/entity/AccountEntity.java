package com.nttdata.accountmovement.infrastructure.output.repository.entity;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("account")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Generated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity {

  @Id
  @Column("id")
  String accountNumber;
  @Column("account_type")
  String accountType;
  @Column("opening_balance")
  BigDecimal openingBalance;
  @Column("status")
  String status;
}
