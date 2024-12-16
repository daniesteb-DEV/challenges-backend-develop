package com.nttdata.accountmovement.infrastructure.output.repository.impl;

import com.nttdata.accountmovement.infrastructure.exception.NotFoundEntityException;
import com.nttdata.accountmovement.infrastructure.output.repository.MovementRepository;
import com.nttdata.accountmovement.infrastructure.output.repository.PostgresMovementRepository;
import com.nttdata.accountmovement.infrastructure.output.repository.entity.MovementEntity;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostgresMovementRepositoryImpl implements PostgresMovementRepository {

  private final MovementRepository movementRepository;

  @Override
  public Flux<MovementEntity> findByFilter(String customerId,
                                           OffsetDateTime startDate,
                                           OffsetDateTime endDate) {
    log.info("|-> [repository] findByFilter start");
    return movementRepository.findByFilter(customerId, startDate, endDate)
        .switchIfEmpty(Mono.error(new NotFoundEntityException("Movement")))
        .doOnError(error -> log.error(
                       "|-> [repository] findByFilter finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<MovementEntity> findByMovementUuid(String movementUuid) {
    log.info("|-> [repository] findByMovementUuid start");
    return movementRepository.findByMovementUuid(movementUuid)
        .switchIfEmpty(Mono.error(new NotFoundEntityException("Movement")))
        .doOnSuccess(response -> log.info(
            "|-> [repository] findByMovementUuid finished successfully."))
        .doOnError(error -> log.error(
                       "|-> [repository] findByMovementUuid finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<MovementEntity> saveMovement(MovementEntity movementEntity) {
    log.info("|-> [repository] saveMovement start");
    return movementRepository.save(movementEntity)
        .doOnSuccess(response -> log.info(
            "|-> [repository] saveMovement finished successfully."))
        .doOnError(error -> log.error(
                       "|-> [repository] saveMovement finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Void> deleteMovement(Long movementId) {
    log.info("|-> [repository] deleteMovement start");
    return movementRepository.deleteById(movementId)
        .doOnSuccess(response -> log.info(
            "|-> [repository] deleteMovement finished successfully."))
        .doOnError(error -> log.error(
                       "|-> [repository] deleteMovement finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }
}
