package com.nttdata.accountmovement.infrastructure.output.repository;

import com.nttdata.accountmovement.infrastructure.output.repository.entity.MovementEntity;
import java.time.OffsetDateTime;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementRepository extends ReactiveCrudRepository<MovementEntity, Long> {

  Mono<MovementEntity> findByMovementUuid(String movementUuid);

  @Query("select m.* from movement m inner join account a on a.customer_id = :customerId where m.movement_date between :startDate and :endDate")
  Flux<MovementEntity> findByFilter(String customerId,
                                    OffsetDateTime startDate,
                                    OffsetDateTime endDate);

}
