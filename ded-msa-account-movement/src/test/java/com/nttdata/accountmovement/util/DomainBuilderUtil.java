package com.nttdata.accountmovement.util;

import com.nttdata.accountmovement.domain.Account;
import com.nttdata.accountmovement.domain.Customer;
import com.nttdata.accountmovement.domain.Movement;
import com.nttdata.accountmovement.domain.MovementReport;
import com.nttdata.accountmovement.domain.MovementReportResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DomainBuilderUtil {

  @NonNull
  public static Account buildAccount(Long accountId,
                                     String accountNumber,
                                     String accountType,
                                     BigDecimal openingBalance,
                                     String status,
                                     String customerId) {
    return Account.builder()
        .accountId(accountId)
        .accountNumber(accountNumber)
        .accountType(accountType)
        .openingBalance(openingBalance)
        .status(status)
        .customerId(customerId)
        .build();
  }

  @NonNull
  public static Customer buildCustomer(String customerId, String customerName) {
    return Customer.builder().customerId(customerId).customerName(customerName).build();
  }

  @NonNull
  public static Movement buildMovement(Long movementId,
                                       String accountNumber,
                                       String movementUuid,
                                       LocalDateTime movementDate,
                                       BigDecimal amount,
                                       String movementType,
                                       BigDecimal balance) {
    return Movement.builder()
        .movementId(movementId)
        .accountNumber(accountNumber)
        .movementUuid(movementUuid)
        .movementDate(movementDate)
        .amount(amount)
        .movementType(movementType)
        .balance(balance)
        .build();
  }

  @NonNull
  public static MovementReport buildMovementReport(Account account,
                                                   List<Movement> movements) {
    return MovementReport.builder().account(account).movements(movements).build();
  }

  @NonNull
  public static MovementReportResponse buildMovementReportResponse(Customer customer,
                                                                   List<MovementReport> accountMovements) {
    return MovementReportResponse.builder()
        .customer(customer)
        .accountMovements(accountMovements)
        .build();
  }

}
