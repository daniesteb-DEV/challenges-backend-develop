package com.nttdata.accountmovement.infrastructure.output.repository;

import com.nttdata.accountmovement.infrastructure.output.repository.entity.MovementEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface MovementRepository extends ReactiveCrudRepository<MovementEntity, String> {

  Mono<MovementEntity> findByMovementId(String id);

}
