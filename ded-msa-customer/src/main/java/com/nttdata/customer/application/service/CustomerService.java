package com.nttdata.customer.application.service;

import com.nttdata.customer.application.input.port.CustomerServicePort;
import com.nttdata.customer.application.output.port.RepositoryServicePort;
import com.nttdata.customer.domain.Customer;
import com.nttdata.customer.infrastructure.input.adapter.rest.mapper.CustomerControllerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService implements CustomerServicePort {

  private final RepositoryServicePort repositoryServicePort;
  private final CustomerControllerMapper customerControllerMapper;

  @Override
  public Mono<Customer> getCustomer(String customerId) {
    log.info("|-> [service] getCustomer start ");
    return repositoryServicePort.findCustomerByPersonId(customerId)
        .doOnSuccess(response -> log.info("<-| [service] getCustomer finished successfully"))
        .doOnError(error -> log.error(
                       "<-| [service] getCustomer finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Customer> registerCustomer(Customer customer) {
    log.info("|-> [service] registerCustomer start ");
    return repositoryServicePort.saveCustomer(customer)
        .doOnSuccess(response -> log.info("<-| [service] registerCustomer finished successfully"))
        .doOnError(error -> log.error(
                       "<-| [service] registerCustomer finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Customer> updateCustomer(Customer customer, String personId) {
    log.info("|-> [service] updateCustomer start ");
    return repositoryServicePort.findCustomerByPersonId(personId)
        .map(customerResponse -> {
               customerControllerMapper.updateCustomer(
                   customerResponse,
                   customer
               );
               return customerResponse;
             }
        )
        .flatMap(customerUpdated -> repositoryServicePort.updateCustomer(customerUpdated, personId))
        .doOnSuccess(response -> log.info("|-> [service] updateCustomer finished successfully"))
        .doOnError(error -> log.error(
                       "|-> [service] updateCustomer finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }

  @Override
  public Mono<Boolean> deleteCustomer(String personId) {
    log.info("|-> [service] deleteCustomer start ");
    return repositoryServicePort.deleteCustomer(personId)
        .doOnSuccess(response -> log.info("|-> [service] deleteCustomer finished successfully"))
        .doOnError(error -> log.error(
                       "|-> [service] deleteCustomer finished with error. ErrorDetail: {}",
                       error.getMessage()
                   )
        );
  }
}
