package com.nttdata.accountmovement.infrastructure.output.repository.impl;

import com.nttdata.accountmovement.infrastructure.output.repository.MovementRepository;
import com.nttdata.accountmovement.infrastructure.output.repository.PostgresMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgresMovementRepositoryImpl implements PostgresMovementRepository {

  private final MovementRepository movementRepository;

}
