package com.nttdata.accountmovement.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Generated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movement {

  Long movementId;
  String accountNumber;
  String movementUuid;
  LocalDateTime movementDate;
  BigDecimal amount;
  String movementType;
  BigDecimal balance;
}
