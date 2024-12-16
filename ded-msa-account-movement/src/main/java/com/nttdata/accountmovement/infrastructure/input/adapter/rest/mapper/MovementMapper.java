package com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper;

import com.nttdata.accountmovement.domain.Account;
import com.nttdata.accountmovement.domain.Customer;
import com.nttdata.accountmovement.domain.Movement;
import com.nttdata.accountmovement.domain.MovementReport;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.GetMovementByFilterResponse;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.MovementReportResponse;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.MovementResponse;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.PostMovementResponse;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.PutMovementResponse;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface MovementMapper {

  void updateMovement(@MappingTarget Movement movement, Movement movementUpdate);

  @Mapping(target = "accountNumber", source = "accountId")
  @Mapping(target = "movementDate", ignore = true)
  @Mapping(target = "balance", ignore = true)
  @Mapping(target = "movementId", ignore = true)
  @Mapping(target = "movementUuid", ignore = true)
  Movement toMovement(com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.Movement movement);

  @Mapping(target = "accountId", source = "accountNumber")
  @Mapping(target = "date", source = "movementDate", qualifiedByName = "convertLocalDatetimeToOffsetDateTime")
  MovementResponse toMovementResponse(Movement movement);

  @Mapping(target = "movementId", source = "movementUuid")
  PostMovementResponse toPostMovementResponse(Movement movement);

  @Mapping(target = "movementId", source = "movementUuid")
  PutMovementResponse toPutMovementResponse(Movement movement);

  @Mapping(target = "movements", source = "movementList")
  MovementReport toMovementReport(Account account, List<Movement> movementList);

  @Mapping(target = "customer", source = "customer")
  @Mapping(target = "accountMovements", source = "accountMovements")
  GetMovementByFilterResponse toGetMovementByFilterResponse(com.nttdata.accountmovement.domain.MovementReportResponse movementReportResponse);

  @Mapping(target = "date", source = "movementDate", qualifiedByName = "convertLocalDatetimeToOffsetDateTime")
  MovementReportResponse toMovementReportResponse(Movement movement);

  @Mapping(target = "customer", source = "customer")
  @Mapping(target = "accountMovements", source = "movementReportList")
  com.nttdata.accountmovement.domain.MovementReportResponse toMovementReportResponse(Customer customer,
                                                                                     List<MovementReport> movementReportList);


  @Named("convertLocalDatetimeToOffsetDateTime")
  static OffsetDateTime convertLocalDatetimeToOffsetDateTime(LocalDateTime dateToConvert) {
    return dateToConvert.atOffset(ZoneOffset.ofHours(1));
  }

}
