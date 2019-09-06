package org.csu.mypetstore_spring.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.csu.mypetstore_spring.domain.Account;

@Mapper
public interface AccountMapper {

    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(Account account);

    void insertAccount(Account account);

    void insertProfile(Account account);

    void insertSignon(Account account);

    void updateAccount(Account account);

    void updateProfile(Account account);

    void updateSignon(Account account);
}
