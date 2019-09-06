package org.csu.mypetstore_spring.service;

import org.csu.mypetstore_spring.domain.Account;
import org.csu.mypetstore_spring.persistence.AccountMapper;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    Account getAccount(String username) ;

    Account getAccount(String username, String password) ;

    void insertAccount(Account account) ;

    void updateAccount(Account account) ;
}
