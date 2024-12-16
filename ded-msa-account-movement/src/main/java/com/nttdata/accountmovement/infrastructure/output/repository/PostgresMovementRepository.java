package com.nttdata.accountmovement.infrastructure.output.repository;

import com.nttdata.accountmovement.infrastructure.output.repository.entity.MovementEntity;
import java.time.OffsetDateTime;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostgresMovementRepository {

  Flux<MovementEntity> findByFilter(String customerId,
                                    OffsetDateTime startDate,
                                    OffsetDateTime endDate);

  Mono<MovementEntity> findByMovementUuid(String movementUuid);

  Mono<MovementEntity> saveMovement(MovementEntity movementEntity);

  Mono<Void> deleteMovement(Long movementId);
}
