package com.nttdata.accountmovement.infrastructure.output.repository.mapper;


import com.nttdata.accountmovement.domain.Account;
import com.nttdata.accountmovement.infrastructure.output.repository.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PostgresAccountMapper {

  @Mapping(target = "accountId", source = "id")
  @Mapping(target = "openingBalance", source = "openingBalance")
  Account toAccount(AccountEntity accountEntity);

  @Mapping(target = "id", source = "accountId")
  AccountEntity toAccountEntity(Account account);
}
