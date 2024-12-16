package com.nttdata.accountmovement.application.input.port;

import com.nttdata.accountmovement.domain.Movement;
import com.nttdata.accountmovement.domain.MovementReport;
import java.time.OffsetDateTime;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementServicePort {

  Flux<MovementReport> retrieveMovementsByFilter(String customerId,
                                                 OffsetDateTime startDate,
                                                 OffsetDateTime endDate);

  Mono<Movement> retrieveMovement(String movementUuid);

  Mono<Movement> registerMovement(Movement movement);

  Mono<Movement> updateMovement(Movement movement, String movementUuid);

  Mono<Boolean> removeMovement(String movementUuid);

}
