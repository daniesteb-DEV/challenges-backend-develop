package com.nttdata.accountmovement.infrastructure.output.repository.mapper;

import com.nttdata.accountmovement.domain.Movement;
import com.nttdata.accountmovement.infrastructure.output.repository.entity.MovementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PostgresMovementMapper {

  @Mapping(target = "movementId", source = "id")
  @Mapping(target = "movementDate", source = "movementDate")
  Movement toMovement(MovementEntity movementEntity);

  @Mapping(target = "id", source = "movementId")
  @Mapping(target = "movementDate", expression = "java(java.time.LocalDateTime.now())")
  @Mapping(target = "movementUuid", expression = "java(java.util.UUID.randomUUID().toString())")
  MovementEntity toMovementEntity(Movement movement);
}
