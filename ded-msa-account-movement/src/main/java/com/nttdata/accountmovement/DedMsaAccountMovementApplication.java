package com.nttdata.accountmovement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class DedMsaAccountMovementApplication {

  public static void main(String[] args) {
    SpringApplication.run(DedMsaAccountMovementApplication.class, args);
  }

}
