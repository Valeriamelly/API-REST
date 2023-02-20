package com.example.apitransfer239919.repository;

import com.example.apitransfer239919.model.Account;
import com.example.apitransfer239919.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
