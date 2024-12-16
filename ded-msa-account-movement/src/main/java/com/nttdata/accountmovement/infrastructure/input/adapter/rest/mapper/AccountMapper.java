package com.nttdata.accountmovement.infrastructure.input.adapter.rest.mapper;

import com.nttdata.accountmovement.domain.Account;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.AccountUpdate;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.PostAccountResponse;
import com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.PutAccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AccountMapper {

  @Mapping(target = "accountType", source = "accountType")
  @Mapping(target = "openingBalance", source = "openingBalance")
  @Mapping(target = "status", source = "status")
  void updateAccount(@MappingTarget Account account, Account accountUpdate);

  com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.Account toAccount(Account account);

  @Mapping(target = "accountId", ignore = true)
  Account toAccount(com.nttdata.accountmovement.infrastructure.input.adapter.rest.models.Account account);

  @Mapping(target = "accountId", ignore = true)
  @Mapping(target = "accountNumber", ignore = true)
  @Mapping(target = "customerId", ignore = true)
  @Mapping(target = "accountType", source = "accountType")
  @Mapping(target = "openingBalance", source = "openingBalance")
  @Mapping(target = "status", source = "status")
  Account toAccount(AccountUpdate accountUpdate);

  PostAccountResponse toPostAccountResponse(Account account);

  PutAccountResponse toPutAccountResponse(Account account);
}
