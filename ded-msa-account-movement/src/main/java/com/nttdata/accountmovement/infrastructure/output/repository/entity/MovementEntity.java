package com.nttdata.accountmovement.infrastructure.output.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public class MovementEntity {

  @Id
  @Column("id")
  String movementId;
  @Column("movement_date")
  LocalDateTime movementDate;
  @Column("movement_type")
  String movementType;
  @Column("amount")
  BigDecimal amount;
  @Column("balance")
  BigDecimal balance;

}
