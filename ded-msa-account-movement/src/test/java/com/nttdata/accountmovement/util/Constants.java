package com.nttdata.accountmovement.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

  public final static Long ACCOUNT_ID = 1L;
  public final static String ACCOUNT_NUMBER = "22037677";
  public final static String ACCOUNT_TYPE = "Ahorros";
  public final static BigDecimal OPENING_BALANCE = BigDecimal.TEN;
  public final static String ACCOUNT_STATUS = "True";
  public final static String CUSTOMER_IDENTIFICATION = "1725374134";
  public final static Long CUSTOMER_ID = 1L;
  public final static String CUSTOMER_NAME = "Daniel Jimenez";
  public final static Long MOVEMENT_ID = 1L;
  public final static String MOVEMENT_UUID = "a10bae03-279d-4724-ba5b-bd1353f87295";
  public final static LocalDateTime MOVEMENT_DATE = LocalDateTime.now();
  public final static BigDecimal MOVEMENT_AMOUNT = BigDecimal.TEN;
  public final static BigDecimal MOVEMENT_BALANCE = BigDecimal.TEN;
  public final static String MOVEMENT_TYPE = "Deposito";
  public final static OffsetDateTime START_DATE = OffsetDateTime.now().minusDays(10);
  public final static OffsetDateTime END_DATE = OffsetDateTime.now().plusDays(10);
}
