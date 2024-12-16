package com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper;

import com.nttdata.accountmovement.domain.Account;
import com.nttdata.accountmovement.domain.Movement;
import com.nttdata.accountmovement.domain.MovementReport;
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
  com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.Movement toMovement(Movement movement);

  @Mapping(target = "accountId", source = "accountNumber")
  @Mapping(target = "date", source = "movementDate", qualifiedByName = "convertLocalDatetimeToOffsetDateTime")
  MovementResponse toMovementResponse(Movement movement);

  @Mapping(target = "movementId", source = "movementUuid")
  PostMovementResponse toPostMovementResponse(Movement movement);

  @Mapping(target = "movementId", source = "movementUuid")
  PutMovementResponse toPutMovementResponse(Movement movement);

  @Mapping(target = "accountNumber", source = "account.accountNumber")
  @Mapping(target = "movements", source = "movementList")
  MovementReport toMovementReport(Account account, List<Movement> movementList);

  @Mapping(target = "accountId", source = "accountNumber")
  com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.MovementReport toMovementReport(
      MovementReport movementReport);

  @Mapping(target = "date", source = "movementDate", qualifiedByName = "convertLocalDatetimeToOffsetDateTime")
  MovementReportResponse toMovementReportResponse(Movement movement);

  @Named("convertLocalDatetimeToOffsetDateTime")
  static OffsetDateTime convertLocalDatetimeToOffsetDateTime(LocalDateTime dateToConvert) {
    return dateToConvert.atOffset(ZoneOffset.ofHours(1));
  }

}