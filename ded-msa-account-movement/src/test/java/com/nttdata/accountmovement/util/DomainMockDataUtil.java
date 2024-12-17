package com.nttdata.accountmovement.util;

import static com.nttdata.accountmovement.util.Constants.ACCOUNT_ID;
import static com.nttdata.accountmovement.util.Constants.ACCOUNT_NUMBER;
import static com.nttdata.accountmovement.util.Constants.ACCOUNT_STATUS;
import static com.nttdata.accountmovement.util.Constants.ACCOUNT_TYPE;
import static com.nttdata.accountmovement.util.Constants.CUSTOMER_IDENTIFICATION;
import static com.nttdata.accountmovement.util.Constants.CUSTOMER_NAME;
import static com.nttdata.accountmovement.util.Constants.MOVEMENT_AMOUNT;
import static com.nttdata.accountmovement.util.Constants.MOVEMENT_BALANCE;
import static com.nttdata.accountmovement.util.Constants.MOVEMENT_DATE;
import static com.nttdata.accountmovement.util.Constants.MOVEMENT_ID;
import static com.nttdata.accountmovement.util.Constants.MOVEMENT_TYPE;
import static com.nttdata.accountmovement.util.Constants.MOVEMENT_UUID;
import static com.nttdata.accountmovement.util.Constants.OPENING_BALANCE;
import static com.nttdata.accountmovement.util.DomainBuilderUtil.buildAccount;
import static com.nttdata.accountmovement.util.DomainBuilderUtil.buildCustomer;
import static com.nttdata.accountmovement.util.DomainBuilderUtil.buildMovement;
import static com.nttdata.accountmovement.util.DomainBuilderUtil.buildMovementReport;
import static com.nttdata.accountmovement.util.DomainBuilderUtil.buildMovementReportResponse;

import com.nttdata.accountmovement.domain.Account;
import com.nttdata.accountmovement.domain.Customer;
import com.nttdata.accountmovement.domain.Movement;
import com.nttdata.accountmovement.domain.MovementReport;
import com.nttdata.accountmovement.domain.MovementReportResponse;
import java.util.List;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DomainMockDataUtil {

  @NonNull
  public static Account getAccount() {
    return buildAccount(
        ACCOUNT_ID,
        ACCOUNT_NUMBER,
        ACCOUNT_TYPE,
        OPENING_BALANCE,
        ACCOUNT_STATUS,
        CUSTOMER_IDENTIFICATION
    );
  }

  @NonNull
  public static Customer getCustomer() {
    return buildCustomer(CUSTOMER_IDENTIFICATION, CUSTOMER_NAME);
  }

  @NonNull
  public static Movement getMovement() {
    return buildMovement(
        MOVEMENT_ID,
        ACCOUNT_NUMBER,
        MOVEMENT_UUID,
        MOVEMENT_DATE,
        MOVEMENT_AMOUNT,
        MOVEMENT_TYPE,
        MOVEMENT_BALANCE
    );
  }

  @NonNull
  public static MovementReport getMovementReport() {
    return buildMovementReport(getAccount(), List.of(getMovement()));
  }

  @NonNull
  public static MovementReportResponse getMovementReportResponse() {
    return buildMovementReportResponse(getCustomer(), List.of(getMovementReport()));
  }
}
